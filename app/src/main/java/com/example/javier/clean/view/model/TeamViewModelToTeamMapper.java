package com.example.javier.clean.view.model;

import com.example.javier.clean.data.repository.datasource.mapper.Mapper;
import com.example.javier.clean.domain.model.Team;

import javax.inject.Inject;

public class TeamViewModelToTeamMapper extends Mapper<TeamViewModel, Team> {

    @Inject
    TeamViewModelToTeamMapper() {
    }

    @Override public Team map(TeamViewModel value) {
        throw new UnsupportedOperationException();
    }

    @Override public TeamViewModel reverseMap(Team value) {
        TeamViewModel teamViewModel = new TeamViewModel();
        teamViewModel.setFlag(value.getFlag());
        teamViewModel.setName(value.getName());
        teamViewModel.setPictureOfFlag(value.getImageFlag());
        teamViewModel.setPictureOfProfile(value.getImageProfile());
        teamViewModel.setPictureOfHeader(value.getImageHeader());
        teamViewModel.setPictureOfDetail(value.getImageDetail());
        teamViewModel.setDisclaimer(value.getDisclaimer());
        teamViewModel.setBestResult(value.getBestResult());
        teamViewModel.setCoach(value.getCoach());
        teamViewModel.setLeadingScorer(value.getLeadingScorer());
        teamViewModel.setNickName(value.getNickName());
        teamViewModel.setStadium(value.getStadium());
        teamViewModel.setDescriptionPart1(value.getDescriptionPart1());
        teamViewModel.setMatchesPlayed(value.getMatchesPlayed());
        teamViewModel.setOverall(value.getOverall());
        teamViewModel.setFinalTournament(value.getFinalTournament());
        teamViewModel.setDescriptionPart2(value.getDescriptionPart2());
        teamViewModel.setDescriptionPart3(value.getDescriptionPart3());
        return teamViewModel;
    }
}
