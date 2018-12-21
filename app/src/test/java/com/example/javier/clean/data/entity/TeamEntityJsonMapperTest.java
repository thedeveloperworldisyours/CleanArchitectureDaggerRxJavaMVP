package com.example.javier.clean.data.entity;
import com.example.javier.clean.data.repository.datasource.mapper.TeamEntityJsonMapper;
import com.example.javier.clean.data.entity.data.FakeTeamLocalAPI;
import com.google.gson.JsonSyntaxException;

import java.util.Collection;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
public class TeamEntityJsonMapperTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();
    private TeamEntityJsonMapper teamEntityJsonMapper;

    @Before
    public void setUp() {
        teamEntityJsonMapper = new TeamEntityJsonMapper();
    }
    @Test
    public void givenTransformCollectionTeamEntityCorrectly() {
        final String FAKE_JSON_RESPONSE_TEAM_COLLECTION =
                FakeTeamLocalAPI.getJsonResponseTeamCollection();
        Collection<TeamEntity> teamEntities =
                teamEntityJsonMapper.transformTeamEntityCollection(FAKE_JSON_RESPONSE_TEAM_COLLECTION);
        final TeamEntity teamEntityOne = ((TeamEntity) teamEntities.toArray()[0]);
        final TeamEntity teamEntityTwo = ((TeamEntity) teamEntities.toArray()[1]);
        final TeamEntity teamEntityThree = ((TeamEntity) teamEntities.toArray()[2]);
        assertThat(teamEntityOne.getTeamFlag(), is("ALB"));
        assertThat(teamEntityTwo.getTeamFlag(), is("AUT"));
        assertThat(teamEntityThree.getTeamFlag(), is("BEL"));
        assertThat(teamEntities.size(), is(3));
    }

    @Test
    public void givenExpectedExceptionTransformUserEntityCollectionNotValidResponse() {
        expectedException.expect(JsonSyntaxException.class);
        teamEntityJsonMapper.transformTeamEntityCollection("Expects a json array like response");
    }

    @Test
    public void givenExpectedExceptionTransformUserEntityNotValidResponse() {
        expectedException.expect(JsonSyntaxException.class);
        teamEntityJsonMapper.transformTeamEntity("Expects a json object like response");
    }
}
