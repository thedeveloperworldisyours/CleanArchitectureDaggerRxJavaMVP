package com.example.javier.clean.data.local;

import android.content.Context;
import com.example.javier.clean.data.entity.TeamEntity;
import com.example.javier.clean.data.repository.datasource.mapper.TeamEntityJsonMapper;
import io.reactivex.Observable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class LocalImpl implements LocalApi{

    private final Context context;
    private final TeamEntityJsonMapper teamEntityJsonMapper;

    public LocalImpl(Context context, TeamEntityJsonMapper teamEntityJsonMapper) {
        this.context = context;
        this.teamEntityJsonMapper = teamEntityJsonMapper;
    }

    @Override
    public Observable<List<TeamEntity>> teamEntityList() {
        return Observable.create(emitter -> {
            List<TeamEntity> teamEntityList = getAll();
            if (teamEntityList != null) {
                emitter.onNext(teamEntityList);
                emitter.onComplete();
            } else {
                emitter.onError(
                        new Throwable("Error getting team data list from the local json (euro_data.json)"));
            }
        });
    }

    @Override
    public Observable<TeamEntity> teamEntity(String flag) {
        return Observable.create(emitter -> {
            TeamEntity teamEntity = getByFlag(flag);
            if (teamEntity != null) {
                emitter.onNext(teamEntity);
                emitter.onComplete();
            } else {
                emitter.onError(
                        new Throwable("Error getting team data from the local json (euro_data.json)")
                );
            }
        });
    }

    private List<TeamEntity> getAll() {
        return teamEntityJsonMapper.transformTeamEntityCollection(getResponseFromLocalJson());
    }

    private TeamEntity getByFlag(String flag) {
        TeamEntity result = null;
        for (TeamEntity entity : getAll()) {
            if (entity.getTeamFlag().equals(flag)) {
                result = entity;
                break;
            }
        }
        return result;
    }

    private String getResponseFromLocalJson() {
        final String EURO_DATA_FILE = "euro_data.json";
        String str = "";
        try {
            StringBuilder builder = new StringBuilder();
            InputStream json = context.getAssets().open(EURO_DATA_FILE);
            BufferedReader in = new BufferedReader(new InputStreamReader(json));

            while ((str = in.readLine()) != null) {
                builder.append(str);
            }
            in.close();
            str = builder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str;
    }
}
