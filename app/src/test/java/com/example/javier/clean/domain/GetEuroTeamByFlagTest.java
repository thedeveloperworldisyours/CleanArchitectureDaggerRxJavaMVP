package com.example.javier.clean.domain;

import com.example.javier.clean.data.repository.TeamsRepository;
import com.example.javier.clean.domain.usecase.GetEuroTeamByFlag;

import io.reactivex.schedulers.Schedulers;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;

public class GetEuroTeamByFlagTest {

    private static final String ANY_FLAG_OF_EURO_TEAMS = "ESP";
    @Mock
    private TeamsRepository repository;
    private GetEuroTeamByFlag getEuroTeamByFlag;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        getEuroTeamByFlag = givenATeamByFlagUseCase();
    }

    @Test
    public void givenAConcreteUseCaseOfGetEuroTeamByFlag() {
        assertThat(getEuroTeamByFlag, instanceOf(GetEuroTeamByFlag.class));
    }

    @Test
    public void getTeamByFlagObservableFromRepository() {
        getEuroTeamByFlag.searchTeamByFlag(ANY_FLAG_OF_EURO_TEAMS);
        getEuroTeamByFlag.createObservableUseCase();
        Mockito.verify(repository).team(ANY_FLAG_OF_EURO_TEAMS);
        Mockito.verifyNoMoreInteractions(repository);
    }

    private GetEuroTeamByFlag givenATeamByFlagUseCase() {
        return new GetEuroTeamByFlag(Schedulers.trampoline(), Schedulers.trampoline(), repository);
    }
}
