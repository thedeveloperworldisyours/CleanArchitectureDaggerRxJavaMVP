package com.example.javier.clean.domain.usecase;

import com.example.javier.clean.data.repository.Repository;
import com.example.javier.clean.domain.model.Team;
import io.reactivex.Observable;
import io.reactivex.Scheduler;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

public class GetEuroTeams extends UseCase<List<Team>> {

    private final Repository repository;

    @Inject
    public GetEuroTeams(@Named("executor_thread") Scheduler executorThread,
                        @Named("ui_thread") Scheduler uiThread, Repository repository) {
        super(executorThread, uiThread);
        this.repository = repository;
    }

    @Override
    public Observable<List<Team>> createObservableUseCase() {
        return this.repository.teamList();
    }
}
