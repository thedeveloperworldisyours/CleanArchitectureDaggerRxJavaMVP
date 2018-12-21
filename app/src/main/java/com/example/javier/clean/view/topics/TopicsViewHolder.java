package com.example.javier.clean.view.topics;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.javier.clean.R;
import com.example.javier.clean.view.model.TeamViewModel;
import com.example.javier.clean.view.model.TopicViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TopicsViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.title_topic)
    TextView title;
    private final TopicsPresenter topicsPresenter;

    public TopicsViewHolder(@NonNull View itemView, @NonNull TopicsPresenter topicsPresenter) {
        super(itemView);
        this.topicsPresenter = topicsPresenter;
        ButterKnife.bind(this, itemView);
    }

    public void render(TopicViewModel topic) {
        renderTopicName(topic.getName());
    }

    private void renderTopicName(String name) {
        title.setText(name);
    }
}
