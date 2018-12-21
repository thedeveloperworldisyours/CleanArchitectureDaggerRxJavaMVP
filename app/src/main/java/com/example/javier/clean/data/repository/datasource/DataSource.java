package com.example.javier.clean.data.repository.datasource;

import com.example.javier.clean.data.entity.TeamEntity;
import com.example.javier.clean.data.entity.Topics;
import io.reactivex.Observable;

import java.util.List;

public interface DataSource {

    Observable<List<TeamEntity>> teamEntityList();

    Observable<TeamEntity> teamEntity(final String flag);

    Observable<List<Topics>> getTopics();
}
