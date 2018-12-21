package com.example.javier.clean.data.remote;

import com.example.javier.clean.data.entity.Topics;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

import javax.inject.Inject;
import java.util.List;

public class RemoteImpl implements RemoteApi {
    Retrofit retrofit;
    private RemoteApi remoteApi;

    @Inject
    public RemoteImpl(Retrofit retrofit) {
        this.retrofit = retrofit;
        remoteApi = retrofit.create(RemoteApi.class);
    }

    @Override
    public Observable<List<Topics>> getTopicsRx() {
        return Observable.create(emitter ->

                remoteApi.getTopicsRx().subscribeOn(Schedulers.io())
                        .cache()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new DisposableObserver<List<Topics>>() {
                            @Override
                            public void onNext(List<Topics> topics) {
                                emitter.onNext(topics);
                            }

                            @Override
                            public void onError(Throwable e) {
                                emitter.onError(e);
                            }

                            @Override
                            public void onComplete() {
                                emitter.onComplete();
                            }
                        }));

    }
}
