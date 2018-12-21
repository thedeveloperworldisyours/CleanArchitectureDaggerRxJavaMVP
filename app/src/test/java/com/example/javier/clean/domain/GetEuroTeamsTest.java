package com.example.javier.clean.domain;

import com.example.javier.clean.data.repository.Repository;
import com.example.javier.clean.domain.usecase.GetEuroTeamByFlag;
import com.example.javier.clean.domain.usecase.GetEuroTeams;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.reactivex.schedulers.Schedulers;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

public class GetEuroTeamsTest {

    @Mock
    Repository repository;

    GetEuroTeams getEuroTeams;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        getEuroTeams = new GetEuroTeams(Schedulers.trampoline(), Schedulers.trampoline(), repository);
    }

    @Test
    public void givenAConcreteUseCaseOfGetEuroTeams() {
        assertThat(getEuroTeams, instanceOf(GetEuroTeams.class));
    }

    @Test
    public void createObservableUseCaseTest() {
        getEuroTeams.createObservableUseCase();
        verify(repository).teamList();
        verifyNoMoreInteractions(repository);
    }
}
