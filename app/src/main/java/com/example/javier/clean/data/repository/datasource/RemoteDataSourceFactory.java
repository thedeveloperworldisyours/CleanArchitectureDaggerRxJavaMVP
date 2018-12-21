package com.example.javier.clean.data.repository.datasource;

import android.content.Context;
import android.support.annotation.NonNull;
import com.example.javier.clean.data.local.LocalImpl;
import com.example.javier.clean.data.remote.RemoteImpl;
import com.example.javier.clean.data.repository.datasource.mapper.TeamEntityJsonMapper;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class RemoteDataSourceFactory {

    private final Context context;
    public static final String URL_BASE = "https://guessthebeach.herokuapp.com/api/";

    @Inject
    public RemoteDataSourceFactory(@NonNull Context context) {
        this.context = context;
    }

    public DataSource createDataSource() {
        TeamEntityJsonMapper teamEntityJsonMapper = new TeamEntityJsonMapper();
        LocalImpl local = new LocalImpl(context, teamEntityJsonMapper);

        RemoteImpl remote = new RemoteImpl(new Retrofit.Builder()
                .baseUrl(URL_BASE)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build());

        return new RemoteLocalApiDataSource(local, remote);
    }
}
