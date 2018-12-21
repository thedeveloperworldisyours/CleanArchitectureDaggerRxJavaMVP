package com.example.javier.clean.view.teams;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import butterknife.BindView;
import com.example.javier.clean.R;
import com.example.javier.clean.view.EuroApplication;
import com.example.javier.clean.view.base.BaseActivity;
import com.example.javier.clean.view.model.TeamViewModel;

import javax.inject.Inject;
import java.util.List;

public class TeamsActivity extends BaseActivity implements TeamsPresenter.View{


    @Inject
    TeamsPresenter presenter;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.progress_team)
    ProgressBar teamProgress;

    private TeamsAdapter adapter;

    @Override
    public void initView() {
        initializeDagger();
        initializePresenter();
        initializeAdapter();
        initializeRecyclerView();
        presenter.initialize();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.team_activity;
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

    private void initializeAdapter() {
        adapter = new TeamsAdapter(presenter);
    }

    private void initializeRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new android.support.v7.widget.DividerItemDecoration(this,
                android.support.v7.widget.DividerItemDecoration.VERTICAL));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }

    private void initializePresenter() {
        presenter.setView(this);
    }

    @Override
    public void showEuroTeams(List<TeamViewModel> teamList) {
        adapter.addAll(teamList);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showLoading() {
        teamProgress.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
    }

    @Override
    public void hideLoading() {
        teamProgress.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
    }
}
