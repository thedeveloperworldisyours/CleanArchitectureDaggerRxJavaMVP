package com.example.javier.clean.di;

import android.content.Context;
import com.example.javier.clean.view.teams.TeamsActivity;
import com.example.javier.clean.view.topics.TopicsActivity;
import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules = MainModule.class)public interface MainComponent {

    void inject(TeamsActivity activity);

    void inject(TopicsActivity activity);

    Context context();

}
