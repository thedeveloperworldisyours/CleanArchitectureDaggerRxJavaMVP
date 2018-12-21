package com.example.javier.clean.di;

import android.content.Context;
import com.example.javier.clean.data.repository.Repository;
import com.example.javier.clean.data.repository.TeamsRepository;
import com.example.javier.clean.view.EuroApplication;
import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import javax.inject.Named;
import javax.inject.Singleton;

@Module public class MainModule {

    private final EuroApplication euroApplication;

    public MainModule(EuroApplication euroApplication) {
        this.euroApplication = euroApplication;
    }

    @Provides @Singleton Context provideApplicationContext() {
        return euroApplication;
    }

    @Provides @Singleton
    Repository provideRepository(TeamsRepository teamsRepository) {
        return teamsRepository;
    }

    @Provides @Named("executor_thread") Scheduler provideExecutorThread() {
        return Schedulers.io();
    }

    @Provides @Named("ui_thread") Scheduler provideUiThread() {
        return AndroidSchedulers.mainThread();
    }
}
