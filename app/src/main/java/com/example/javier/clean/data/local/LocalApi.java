package com.example.javier.clean.data.local;


import com.example.javier.clean.data.entity.TeamEntity;
import io.reactivex.Observable;
import java.util.List;

public interface LocalApi {

    Observable<List<TeamEntity>> teamEntityList();

    Observable<TeamEntity> teamEntity(final String flag);
}
