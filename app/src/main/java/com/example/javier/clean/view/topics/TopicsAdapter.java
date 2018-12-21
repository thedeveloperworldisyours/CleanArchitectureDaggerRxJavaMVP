package com.example.javier.clean.view.topics;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.javier.clean.R;
import com.example.javier.clean.view.model.TopicViewModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TopicsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final TopicsPresenter presenter;
    private final List<TopicViewModel> topicsList;

    public TopicsAdapter(TopicsPresenter presenter) {
        this.presenter = presenter;
        topicsList = new ArrayList<>();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.topic_row, parent, false);
        return new TopicsViewHolder(view, presenter);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        TopicsViewHolder topicsViewHolder = (TopicsViewHolder) holder;
        TopicViewModel topicViewModel = topicsList.get(position);
        topicsViewHolder.render(topicViewModel);
    }

    @Override
    public int getItemCount() {
        return topicsList.size();
    }

    public void addAll(Collection<TopicViewModel> collection) {
        topicsList.addAll(collection);
    }
}
