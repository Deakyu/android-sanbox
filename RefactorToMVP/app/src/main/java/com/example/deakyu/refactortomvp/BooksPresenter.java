package com.example.deakyu.refactortomvp;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BooksPresenter {
    private BooksView view;
    private BooksInteractor interactor;

    public BooksPresenter(BooksInteractor interactor) {
        this.interactor = interactor;
    }

    public void bind(BooksView view) {
        this.view = view;
    }

    public void unbind() {
        this.view = null;
    }

    public void performSearch(String userInput) {
        String formatUserInput = userInput.trim().replaceAll("\\s+", "+");
        interactor.search(formatUserInput)
                .enqueue(new Callback<List<Book>>() {
                    @Override
                    public void onResponse(Call<List<Book>> call, Response<List<Book>> response) {
                        if(view != null) view.updateUi(response.body());
                    }

                    @Override
                    public void onFailure(Call<List<Book>> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
    }
}
