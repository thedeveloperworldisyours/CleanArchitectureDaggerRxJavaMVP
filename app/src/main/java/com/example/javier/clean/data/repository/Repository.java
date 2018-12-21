package com.example.javier.clean.data.repository;

import com.example.javier.clean.domain.model.Team;
import com.example.javier.clean.domain.model.Topic;
import io.reactivex.Observable;

import java.util.List;

public interface Repository {
    Observable<List<Team>> teamList();

    Observable<Team> team(final String flag);

    Observable<List<Topic>> getTopic();
}
