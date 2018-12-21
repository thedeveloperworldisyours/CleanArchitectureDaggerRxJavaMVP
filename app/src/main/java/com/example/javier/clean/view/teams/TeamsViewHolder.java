package com.example.javier.clean.view.teams;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.bumptech.glide.Glide;
import com.example.javier.clean.R;
import com.example.javier.clean.view.model.TeamViewModel;

public class TeamsViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.image_header)
    ImageView headerImage;
    @BindView(R.id.image_flag)
    ImageView flagImage;
    @BindView(R.id.label_name)
    TextView nameLabel;
    private final TeamsPresenter teamsPresenter;

    public TeamsViewHolder(@NonNull View itemView, @NonNull TeamsPresenter teamsPresenter) {
        super(itemView);
        this.teamsPresenter = teamsPresenter;
        ButterKnife.bind(this, itemView);
    }

    public void render(TeamViewModel team) {
        onItemClick(team);
        renderTeamHeaderImage(team.getPictureOfHeader());
        renderTeamFlagImage(team.getPictureOfFlag());
        renderTeamName(team.getName());
    }

    private void onItemClick(final TeamViewModel teamViewModel) {
//        itemView.setOnClickListener(new View.OnClickListener() {
//            @Override public void onClick(View v) {
//                teamsPresenter.onTeamClicked(teamViewModel);
//            }
//        });
    }

    private void renderTeamHeaderImage(String urlHeaderImage) {
        getImage(urlHeaderImage, headerImage);
    }

    private void renderTeamFlagImage(String urlFlagImage) {
        getImage(urlFlagImage, flagImage);
    }

    private void renderTeamName(String name) {
        nameLabel.setText(name);
    }

    private void getImage(String photo, ImageView photoImageView) {
        Glide.with(getContext()).load(photo).into(photoImageView);
    }

    private Context getContext() {
        return itemView.getContext();
    }
}
