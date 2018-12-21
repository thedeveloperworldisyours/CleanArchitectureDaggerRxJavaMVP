package com.example.javier.clean.data.repository.datasource;

import com.example.javier.clean.data.entity.TeamEntity;
import com.example.javier.clean.data.entity.Topics;
import com.example.javier.clean.data.local.LocalApi;
import com.example.javier.clean.data.remote.RemoteApi;
import io.reactivex.Observable;

import java.util.List;

public class RemoteLocalApiDataSource implements DataSource {

    private final LocalApi localApi;
    private final RemoteApi remoteApi;

    public RemoteLocalApiDataSource(LocalApi localApi, RemoteApi remoteApi) {
        this.localApi = localApi;
        this.remoteApi = remoteApi;
    }

    @Override
    public Observable<List<TeamEntity>> teamEntityList() {
        return localApi.teamEntityList();
    }

    @Override
    public Observable<TeamEntity> teamEntity(String flag) {
        return localApi.teamEntity(flag);
    }

    @Override
    public Observable<List<Topics>> getTopics() {
        return remoteApi.getTopicsRx();
    }
}
