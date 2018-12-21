package com.example.javier.clean.domain;

import com.example.javier.clean.data.repository.Repository;
import com.example.javier.clean.domain.usecase.GetTopics;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.reactivex.schedulers.Schedulers;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.verify;

public class GetTopicsTest {

    @Mock
    Repository repository;

    GetTopics getTopics;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        getTopics = new GetTopics(Schedulers.trampoline(), Schedulers.trampoline(), repository);
    }

    @Test
    public void givenAConcreteUseCaseOfGetTopic() {
        assertThat(getTopics, instanceOf(GetTopics.class));
    }

    @Test
    public void getTopicsObservableFromRepository() {
        getTopics.createObservableUseCase();

        verify(repository).getTopic();
    }
}
