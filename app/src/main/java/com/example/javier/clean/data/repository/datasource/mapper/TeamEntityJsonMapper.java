package com.example.javier.clean.data.repository.datasource.mapper;

import com.example.javier.clean.data.entity.TeamEntity;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import javax.inject.Inject;
import java.lang.reflect.Type;
import java.util.List;

public class TeamEntityJsonMapper {
    private final Gson gson;

    @Inject
    public TeamEntityJsonMapper() {
        gson = new Gson();
    }

    public TeamEntity transformTeamEntity(String teamJsonResponse) throws JsonSyntaxException {
        TeamEntity teamEntity;
        try {
            Type typeTeamEntity = new TypeToken<TeamEntity>() {
            }.getType();
            teamEntity = this.gson.fromJson(teamJsonResponse, typeTeamEntity);
            return teamEntity;
        } catch (JsonSyntaxException exception) {
            exception.printStackTrace();
            throw exception;
        }
    }

    public List<TeamEntity> transformTeamEntityCollection(String teamListJsonResponse)
            throws JsonSyntaxException {
        List<TeamEntity> teamEntityList;
        try {
            Type typeTeamEntityList = new TypeToken<List<TeamEntity>>() {
            }.getType();
            teamEntityList = this.gson.fromJson(teamListJsonResponse, typeTeamEntityList);
            return teamEntityList;
        } catch (JsonSyntaxException exception) {
            exception.printStackTrace();
            throw exception;
        }
    }
}
