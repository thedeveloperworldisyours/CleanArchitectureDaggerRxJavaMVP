package com.example.javier.clean.data.entity;

import com.example.javier.clean.data.repository.datasource.mapper.TeamToTeamEntityMapper;
import com.example.javier.clean.domain.model.Team;
import com.example.javier.clean.data.entity.data.FakeTeamLocalAPI;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TeamToTeamEntityMapperTest {

    private final static String FAKE_TEAM_FLAG = "ALB";
    private final static String FAKE_TEAM_NAME = "Albania";
    @Rule
    public ExpectedException expectedException = ExpectedException.none();
    private TeamToTeamEntityMapper teamToTeamEntityMapper;

    @Before
    public void setUp() {
        teamToTeamEntityMapper = new TeamToTeamEntityMapper();
    }

    @Test
    public void givenTransformTeamEntityToTeam() throws Exception {
        TeamEntity teamEntity = FakeTeamLocalAPI.getFakeTeamEntity();
        Team team = teamToTeamEntityMapper.reverseMap(teamEntity);
        assertThat(team, is(instanceOf(Team.class)));
        assertThat(team.getFlag(), is(FAKE_TEAM_FLAG));
        assertThat(team.getName(), is(FAKE_TEAM_NAME));
    }

    @Test
    public void givenExpectedExceptionTransformUserEntityCollectionNotValidResponse() {
        expectedException.expect(UnsupportedOperationException.class);
        teamToTeamEntityMapper.map(new Team());
    }
}
