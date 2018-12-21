package com.example.javier.clean.view.topics;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.example.javier.clean.R;
import com.example.javier.clean.view.EuroApplication;
import com.example.javier.clean.view.base.BaseActivity;
import com.example.javier.clean.view.model.TopicViewModel;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class TopicsActivity extends BaseActivity implements TopicsPresenter.View{

    @Inject
    TopicsPresenter presenter;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.progress_topic)
    ProgressBar progressBar;

    private TopicsAdapter adapter;
    @Override
    public void initView() {
        initializeDagger();
        initializePresenter();
        initializeAdapter();
        initializeRecyclerView();
        presenter.initialize();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.destroy();
    }

    private void initializeDagger() {
        EuroApplication app = (EuroApplication) getApplication();
        app.getMainComponent().inject(this);
    }
    private void initializePresenter() {
        presenter.setView(this);
    }

    private void initializeAdapter() {
        adapter = new TopicsAdapter(presenter);
    }

    private void initializeRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new android.support.v7.widget.DividerItemDecoration(this,
                android.support.v7.widget.DividerItemDecoration.VERTICAL));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.topic_activity;
    }

    @Override
    public void showTopics(List<TopicViewModel> topicList) {
        adapter.addAll(topicList);
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
    }
}
