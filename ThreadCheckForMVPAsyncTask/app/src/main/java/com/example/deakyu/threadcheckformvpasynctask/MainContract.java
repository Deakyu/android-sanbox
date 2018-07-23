package com.example.deakyu.threadcheckformvpasynctask;

public interface MainContract {
    interface View {
        void displayMessage(String message);
    }

    interface Presenter {
        void bind(MainContract.View view);
        void unbind();
        void onButtonClicked();
    }
}
