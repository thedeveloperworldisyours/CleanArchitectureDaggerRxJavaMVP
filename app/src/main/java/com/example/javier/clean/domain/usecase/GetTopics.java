package com.example.javier.clean.domain.usecase;

import com.example.javier.clean.data.repository.Repository;
import com.example.javier.clean.domain.model.Topic;
import io.reactivex.Observable;
import io.reactivex.Scheduler;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

public class GetTopics extends UseCase<List<Topic>> {
    private final Repository repository;

    @Inject
    public GetTopics(@Named("executor_thread") Scheduler executorThread,
                        @Named("ui_thread") Scheduler uiThread, Repository repository) {
        super(executorThread, uiThread);
        this.repository = repository;
    }

    @Override
    public Observable<List<Topic>> createObservableUseCase() {
        return this.repository.getTopic();
    }
}
