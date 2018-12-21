package com.example.javier.clean.view.teams;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.javier.clean.R;
import com.example.javier.clean.view.model.TeamViewModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TeamsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final TeamsPresenter presenter;
    private final List<TeamViewModel> teamList;

    public TeamsAdapter(TeamsPresenter presenter) {
        this.presenter = presenter;
        teamList = new ArrayList<>();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.team_row, parent, false);
        return new TeamsViewHolder(view, presenter);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        TeamsViewHolder teamViewHolder = (TeamsViewHolder) holder;
        TeamViewModel team = teamList.get(position);
        teamViewHolder.render(team);
    }

    @Override
    public int getItemCount() {
        return teamList.size();
    }

    public void addAll(Collection<TeamViewModel> collection) {
        teamList.addAll(collection);
    }
}
