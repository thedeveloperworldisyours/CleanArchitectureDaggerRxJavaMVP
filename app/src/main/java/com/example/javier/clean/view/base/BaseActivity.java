package com.example.javier.clean.view.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        bindViews();
        initView();
    }

    /**
     * Every object annotated with {@link butterknife} its gonna injected trough butterknife
     */
    private void bindViews() {
        ButterKnife.bind(this);
    }

    /**
     * Use this method to initialize view components. This method is called after {@link
     * BaseActivity#bindViews()}
     */
    public void initView() {
    }

    /**
     * @return The layout id that's gonna be the activity view.
     */
    protected abstract int getLayoutId();
}
