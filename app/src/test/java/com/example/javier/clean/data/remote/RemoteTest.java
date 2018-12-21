package com.example.javier.clean.data.remote;

import com.example.javier.clean.data.entity.Topics;
import com.google.gson.Gson;
import io.reactivex.Scheduler;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.schedulers.ExecutorScheduler;
import io.reactivex.observers.TestObserver;
import io.reactivex.plugins.RxJavaPlugins;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class RemoteTest {

    List<Topics> resul;
    MockWebServer mMockWebServer;

    @Before
    public void setUp() {

        Topics topics = new Topics(1, "Discern The Beach");
        Topics topicsTwo = new Topics(2, "Discern The Football Player");
        resul = new ArrayList();
        resul.add(topics);
        resul.add(topicsTwo);

        mMockWebServer = new MockWebServer();
    }

    @BeforeClass
    public static void setUpRxSchedulers() {
        Scheduler immediate = new Scheduler() {
            @Override
            public Disposable scheduleDirect(@NonNull Runnable run, long delay, @NonNull TimeUnit unit) {
                // this prevents StackOverflowErrors when scheduling with a delay
                return super.scheduleDirect(run, 0, unit);
            }

            @Override
            public Worker createWorker() {
                return new ExecutorScheduler.ExecutorWorker(Runnable::run);
            }
        };

        RxJavaPlugins.setInitIoSchedulerHandler(scheduler -> immediate);
        RxJavaPlugins.setInitComputationSchedulerHandler(scheduler -> immediate);
        RxJavaPlugins.setInitNewThreadSchedulerHandler(scheduler -> immediate);
        RxJavaPlugins.setInitSingleSchedulerHandler(scheduler -> immediate);
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(scheduler -> immediate);
    }

    @Test
    public void severCallWithSuccessful() {
        //Given
        String url = "https://guessthebeach.herokuapp.com/api/";
        mMockWebServer.enqueue(new MockResponse().setBody(new Gson().toJson(resul)));
        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(mMockWebServer.url(url))
                .build();
        RemoteApi remoteDataSource = new RemoteImpl(retrofit);
        TestObserver<List<Topics>> topicsObservable = remoteDataSource.getTopicsRx().test();

        remoteDataSource.getTopicsRx().subscribe(topicsObservable);


        topicsObservable.hasSubscription();
        assertThat(topicsObservable.values().get(0).size(), is(2));
        assertThat(topicsObservable.values().get(0).get(0).getId(), is(1));
        assertThat(topicsObservable.values().get(0).get(1).getId(), is(2));

        assertThat(topicsObservable.values().get(0).get(0).getName(), is("Discern The Beach"));
        assertThat(topicsObservable.values().get(0).get(1).getName(), is("Discern The Football Player"));
    }

    @Test
    public void serverCallWithError() {
        //Given
        String url = "dfdf/";
        resul = new ArrayList();
        mMockWebServer.enqueue(new MockResponse().setBody(new Gson().toJson(resul)));
        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(mMockWebServer.url(url))
                .build();
        RemoteApi remoteDataSource = new RemoteImpl(retrofit);
        TestObserver<List<Topics>> topicsObservable = remoteDataSource.getTopicsRx().test();
        topicsObservable.hasSubscription();
        assertThat(topicsObservable.values().get(0).size(), is(0));
    }
}
