package com.example.javier.clean.domain.usecase;

import com.example.javier.clean.data.repository.Repository;
import com.example.javier.clean.domain.model.Team;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.disposables.CompositeDisposable;

import javax.inject.Inject;
import javax.inject.Named;

public class GetEuroTeamByFlag extends UseCase<Team> {

    private final Repository repository;
    private String flag = "";

    @Inject
    public GetEuroTeamByFlag(@Named("executor_thread") Scheduler executorThread,
                             @Named("ui_thread") Scheduler uiThread, Repository repository) {
        super(executorThread, uiThread);
        this.repository = repository;
    }

    public void searchTeamByFlag(String flag) {
        this.flag = flag;
    }

    @Override
    public Observable<Team> createObservableUseCase() {
        return this.repository.team(flag);
    }
}
