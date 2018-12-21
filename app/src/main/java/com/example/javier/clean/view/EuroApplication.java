package com.example.javier.clean.view;

import android.app.Application;
import com.example.javier.clean.di.DaggerMainComponent;
import com.example.javier.clean.di.MainComponent;
import com.example.javier.clean.di.MainModule;

public class EuroApplication extends Application {

    private MainComponent mainComponent;

    @Override public void onCreate() {
        super.onCreate();
        initializeInjector();
    }

    private void initializeInjector() {
        mainComponent = DaggerMainComponent.builder().mainModule(new MainModule(this)).build();
    }

    public MainComponent getMainComponent() {
        return mainComponent;
    }
}
