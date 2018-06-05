package com.example.deakyu.refactortomvvm;

import com.example.deakyu.refactortomvvm.model.BookSearchResult;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.schedulers.Schedulers;

public class BooksInteractorImple implements BooksInteractor {

    private GoogleBooksService service;

    public BooksInteractorImple() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.googleapis.com")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        service = retrofit.create(GoogleBooksService.class);
    }

    @Override
    public Observable<BookSearchResult> search(String search) {
        return service.search("search+" + search).subscribeOn(Schedulers.io());
    }
}
