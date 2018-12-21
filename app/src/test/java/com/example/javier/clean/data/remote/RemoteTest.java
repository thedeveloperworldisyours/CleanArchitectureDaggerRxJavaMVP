package com.example.javier.clean.data.remote;

import com.example.javier.clean.data.entity.Topics;
import com.google.gson.Gson;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class RemoteTest {

    List<Topics> mResultList;
    MockWebServer mMockWebServer;

    @Before
    public void setUp() {

        Topics topics = new Topics(1, "Discern The Beach");
        Topics topicsTwo = new Topics(2, "Discern The Football Player");
        mResultList = new ArrayList();
        mResultList.add(topics);
        mResultList.add(topicsTwo);

        mMockWebServer = new MockWebServer();

    }
    @Test
    public void severCallWithSuccessful() {
        //Given
        String url = "https://guessthebeach.herokuapp.com/api/";
        mMockWebServer.enqueue(new MockResponse().setBody(new Gson().toJson(mResultList)));
        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(mMockWebServer.url(url))
                .build();
        RemoteApi remoteDataSource = new RemoteImpl(retrofit);

        //When
        remoteDataSource.getTopicsRx().subscribeOn(Schedulers.io())
                .subscribe(new DisposableObserver<List<Topics>>() {
                    @Override
                    public void onNext(List<Topics> topics) {
                        assertThat("Topics count is an unexpected value", topics.size(), is(equalTo(5)));
                    }

                    @Override
                    public void onError(Throwable e) {

                        Assert.fail("Unxpected response :" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }


                });
    }

    @Test
    public void serverCallWithError() {
        //Given
        String url = "dfdf/";
        mMockWebServer.enqueue(new MockResponse().setBody(new Gson().toJson(mResultList)));
        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(mMockWebServer.url(url))
                .build();
        RemoteApi remoteDataSource = new RemoteImpl(retrofit);

        //When
        remoteDataSource.getTopicsRx().subscribeOn(Schedulers.io())
                .subscribe(new DisposableObserver<List<Topics>>() {
                    @Override
                    public void onNext(List<Topics> topics) {
                        assertThat("Topics count is an unexpected value", topics.size(), is(equalTo(2)));
                    }

                    @Override
                    public void onError(Throwable e) {

                        Assert.fail("Unxpected response :" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }


                });
    }
}
