package com.example.javier.clean.data.remote;

import com.example.javier.clean.data.entity.Topics;
import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

import java.util.List;

public interface RemoteApi {
    @GET("topics/")
    Observable<List<Topics>> getTopicsRx();
}
