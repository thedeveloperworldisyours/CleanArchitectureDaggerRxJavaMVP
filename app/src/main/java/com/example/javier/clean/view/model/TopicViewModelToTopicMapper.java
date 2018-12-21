package com.example.javier.clean.view.model;

import com.example.javier.clean.data.repository.datasource.mapper.Mapper;
import com.example.javier.clean.domain.model.Topic;

import javax.inject.Inject;

public class TopicViewModelToTopicMapper  extends Mapper<TopicViewModel, Topic> {

    @Inject
    TopicViewModelToTopicMapper() {
    }

    @Override
    public Topic map(TopicViewModel value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public TopicViewModel reverseMap(Topic value) {
        TopicViewModel topicViewModel = new TopicViewModel();
        topicViewModel.setTopicId(value.getId());
        topicViewModel.setName(value.getName());
        return topicViewModel;
    }
}
