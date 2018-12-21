package com.example.javier.clean.data.repository;

import android.support.annotation.NonNull;
import com.example.javier.clean.data.repository.datasource.DataSource;
import com.example.javier.clean.data.repository.datasource.RemoteDataSourceFactory;
import com.example.javier.clean.data.repository.datasource.mapper.TeamToTeamEntityMapper;
import com.example.javier.clean.data.repository.datasource.mapper.TopicToTopicsMapper;
import com.example.javier.clean.domain.model.Team;
import com.example.javier.clean.domain.model.Topic;
import io.reactivex.Observable;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

@Singleton
public class TeamsRepository implements Repository {

    private final TopicToTopicsMapper topicToTopicsMapper;
    private final TeamToTeamEntityMapper teamToTeamEntityMapper;
    private final DataSource dataSource;

    @Inject
    public TeamsRepository(RemoteDataSourceFactory teamDataSourceFactory,
                           @NonNull TeamToTeamEntityMapper teamToTeamEntityMapper, TopicToTopicsMapper topicToTopicsMapper) {
        this.teamToTeamEntityMapper = teamToTeamEntityMapper;
        this.topicToTopicsMapper = topicToTopicsMapper;
        this.dataSource = teamDataSourceFactory.createDataSource();
    }

    @Override
    public Observable<List<Team>> teamList() {
        return dataSource.teamEntityList().map(teamToTeamEntityMapper::reverseMap);
    }

    @Override
    public Observable<Team> team(String flag) {
        return dataSource.teamEntity(flag).map(teamToTeamEntityMapper::reverseMap);
    }

    @Override
    public Observable<List<Topic>> getTopic() {
        return dataSource.getTopics().map(topicToTopicsMapper::reverseMap);
    }
}
