package com.example.javier.clean.view.teams;

import android.support.annotation.NonNull;
import com.example.javier.clean.domain.model.Team;
import com.example.javier.clean.domain.usecase.GetEuroTeams;
import com.example.javier.clean.view.base.BasePresenter;
import com.example.javier.clean.view.model.TeamViewModel;
import com.example.javier.clean.view.model.TeamViewModelToTeamMapper;
import io.reactivex.observers.DisposableObserver;

import javax.inject.Inject;
import java.util.List;

public class TeamsPresenter extends BasePresenter<TeamsPresenter.View> {

    private GetEuroTeams getEuroTeams;
    private TeamViewModelToTeamMapper mapper;

    @Inject
    public TeamsPresenter(@NonNull GetEuroTeams getEuroTeams,
                          @NonNull TeamViewModelToTeamMapper mapper) {
        this.getEuroTeams = getEuroTeams;
        this.mapper = mapper;
    }

    @Override
    public void initialize() {
        super.initialize();
        getView().showLoading();
        getEuroTeams.execute(new DisposableObserver<List<Team>>() {
            @Override
            public void onNext(List<Team> teams) {
                List<TeamViewModel> teamViewModels = mapper.reverseMap(teams);
                getView().showEuroTeams(teamViewModels);
            }

            @Override
            public void onError(Throwable e) {
                getView().hideLoading();
                e.printStackTrace();
            }

            @Override
            public void onComplete() {
                getView().hideLoading();
            }
        });
    }

    public void destroy() {
        this.getEuroTeams.dispose();
        setView(null);
    }

    public interface View extends BasePresenter.View {

        void showEuroTeams(List<TeamViewModel> teamList);
    }
}
