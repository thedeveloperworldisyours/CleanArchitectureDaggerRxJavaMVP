package com.example.javier.clean.data.repository.datasource.mapper;

import com.example.javier.clean.data.entity.Topics;
import com.example.javier.clean.domain.model.Topic;

import javax.inject.Inject;

public class TopicToTopicsMapper extends Mapper<Topic, Topics>{

    @Inject
    public TopicToTopicsMapper() {

    }

    @Override
    public Topics map(Topic value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Topic reverseMap(Topics value) {
        return new Topic(String.valueOf(value.getId()), value.getName());
    }
}
