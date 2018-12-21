package com.example.javier.clean.view.base;

public class BasePresenter <T extends BasePresenter.View> {

    private T view;

    public T getView() {
        return view;
    }

    public void setView(T view) {
        this.view = view;
    }

    public void initialize() {

    }

    public interface View {

        void showLoading();

        void hideLoading();
    }
}
