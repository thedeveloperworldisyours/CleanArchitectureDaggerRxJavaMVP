package com.example.javier.clean.data.repository;

import com.example.javier.clean.data.repository.datasource.DataSource;
import com.example.javier.clean.data.repository.datasource.RemoteDataSourceFactory;
import com.example.javier.clean.data.repository.datasource.RemoteLocalApiDataSource;
import org.junit.Before;
import org.junit.Test;
import org.robolectric.RuntimeEnvironment;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class TeamDataSourceFactoryTest {

    RemoteDataSourceFactory RemoteDataSourceFactory;

    @Before
    public void setUp() {
        RemoteDataSourceFactory = new RemoteDataSourceFactory(RuntimeEnvironment.application);
    }

    @Test
    public void givenAnInstanceRemoteLocalApiDataSource() {
        DataSource dataSource = RemoteDataSourceFactory.createDataSource();

        assertThat(dataSource, is(notNullValue()));
        assertThat(dataSource, is(instanceOf(RemoteLocalApiDataSource.class)));
    }
}
