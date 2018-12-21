package com.example.javier.clean.data.repository;

import com.example.javier.clean.data.entity.TeamEntity;
import com.example.javier.clean.data.entity.Topics;
import com.example.javier.clean.data.local.LocalApi;
import com.example.javier.clean.data.remote.RemoteApi;
import com.example.javier.clean.data.repository.datasource.DataSource;
import com.example.javier.clean.data.repository.datasource.RemoteLocalApiDataSource;
import io.reactivex.Observable;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

public class RemoteLocalApiDataSourceTest {

    @Mock
    LocalApi localApi;
    @Mock
    RemoteApi remoteApi;

    DataSource dataSource;
    private static final String FLAG = "ESP";

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        dataSource = new RemoteLocalApiDataSource(localApi, remoteApi);
    }

    @Test
    public void verifyTeamEntityList() {
        dataSource.teamEntityList();
        verify(localApi).teamEntityList();
    }

    @Test
    public void verifyTeamEntityTestSuccessful() {
        dataSource.teamEntity(FLAG);
        verify(localApi).teamEntity(FLAG);
    }

    @Test
    public void verifyGetTopic() {
        dataSource.getTopics();
        verify(remoteApi).getTopicsRx();
    }

    @Test
    public void givenAnObservableCollectionTeamEntity() {
        List<TeamEntity> teamEntities = new ArrayList<>();
        Observable<List<TeamEntity>> fakeListObservable = Observable.just(teamEntities);
        given(localApi.teamEntityList()).willReturn(fakeListObservable);
    }

    @Test
    public void givenAnObservableTeamEntity() {

        TeamEntity teamEntity = new TeamEntity();
        Observable<TeamEntity> fakeObservable = Observable.just(teamEntity);
        given(localApi.teamEntity(FLAG)).willReturn(fakeObservable);
    }

    @Test
    public void givenAnObservableGetTopic() {
        List<Topics> topicsList = new ArrayList<>();
        Observable<List<Topics>> fakeObservable = Observable.just(topicsList);
        given(remoteApi.getTopicsRx()).willReturn(fakeObservable);
    }
}
