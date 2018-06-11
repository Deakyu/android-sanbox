package com.example.deakyu.refactortomvp;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BooksInteractorImpl implements BooksInteractor {

    private BookService service;

    public BooksInteractorImpl() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://5b1727a7f5c9b700145511cc.mockapi.io/facevenmodata/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(BookService.class);
    }

    @Override
    public Call<List<Book>> search(String search) {
        return service.search(search);
    }
}
