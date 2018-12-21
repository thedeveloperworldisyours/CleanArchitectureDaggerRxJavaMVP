package com.example.javier.clean.data.repository.datasource.mapper;

import com.example.javier.clean.data.entity.TeamEntity;
import com.example.javier.clean.domain.model.Team;

import javax.inject.Inject;

public class TeamToTeamEntityMapper extends Mapper<Team, TeamEntity> {

    @Inject
    public TeamToTeamEntityMapper() {
    }

    @Override
    public TeamEntity map(Team value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Team reverseMap(TeamEntity value) {
        Team team = new Team();
        team.setFlag(value.getTeamFlag());
        team.setName(value.getTeamName());
        team.setImageFlag(value.getImages().get(0).getImageFlag());
        team.setImageProfile(value.getImages().get(0).getImageProfile());
        team.setImageHeader(value.getImages().get(0).getImageHeader());
        team.setImageDetail(value.getImages().get(0).getImageDetail());
        team.setDisclaimer(value.getDisclaimer());
        team.setBestResult(value.getBestResult());
        team.setCoach(value.getCoach());
        team.setLeadingScorer(value.getLeadingScorer());
        team.setNickName(value.getNickName());
        team.setStadium(value.getStadium());
        team.setDescriptionPart1(value.getGetdescription1());
        team.setMatchesPlayed(value.getMatchesPlayed());
        team.setOverall(value.getTeamOverall());
        team.setFinalTournament(value.getFinalTournament());
        team.setDescriptionPart2(value.getGetDescription2());
        team.setDescriptionPart3(value.getGetDescription3());
        return team;
    }
}

