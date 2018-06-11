package com.example.deakyu.refactortomvp;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BooksPresenterImpl implements BooksPresenter {
    private BooksView view;
    private BooksInteractor interactor;
    private List<Book> currentBookList;

    public BooksPresenterImpl(BooksInteractor interactor) {
        this.interactor = interactor;
    }

    @Override
    public void bind(BooksView view) {
        this.view = view;
    }

    @Override
    public void unbind() {
        this.view = null;
    }

    @Override
    public void performSearch(String userInput) {
        String formatUserInput = userInput.trim().replaceAll("\\s+", "+");
        interactor.search(formatUserInput)
                .enqueue(new Callback<List<Book>>() {
                    @Override
                    public void onResponse(Call<List<Book>> call, Response<List<Book>> response) {
                        if(view != null) {
                            view.updateUi(response.body());
                            currentBookList = response.body();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Book>> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
    }

    @Override
    public List<Book> getCurrentBookList() {
        return currentBookList;
    }
}
