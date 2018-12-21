package com.example.javier.clean.view.topics;

import android.util.Log;
import com.example.javier.clean.domain.model.Topic;
import com.example.javier.clean.domain.usecase.GetTopics;
import com.example.javier.clean.view.base.BasePresenter;
import com.example.javier.clean.view.model.TopicViewModel;
import com.example.javier.clean.view.model.TopicViewModelToTopicMapper;
import io.reactivex.observers.DisposableObserver;

import javax.inject.Inject;
import java.util.List;

public class TopicsPresenter extends BasePresenter<TopicsPresenter.View> {

    private static final String TAG = TopicsPresenter.class.getName();
    GetTopics getTopics;
    TopicViewModelToTopicMapper topicViewModelToTopicMapper;

    @Inject
    public TopicsPresenter(GetTopics getTopics,
                           TopicViewModelToTopicMapper topicToTopicsMapper) {
        this.getTopics = getTopics;
        this.topicViewModelToTopicMapper = topicToTopicsMapper;
    }
    @Override
    public void initialize() {
        super.initialize();
        getView().showLoading();
        getTopics.execute(new DisposableObserver<List<Topic>>() {
            @Override
            public void onNext(List<Topic> topics) {
                Log.d(TAG, topics.toString());
                List<TopicViewModel> topicViewModels = topicViewModelToTopicMapper.reverseMap(topics);
                getView().showTopics(topicViewModels);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, e.toString());
            }

            @Override
            public void onComplete() {
                getView().hideLoading();

            }
        });
    }

    public void destroy() {
        this.getTopics.dispose();
        setView(null);
    }

    public interface View extends BasePresenter.View {

        void showTopics(List<TopicViewModel> teamList);
    }
}
