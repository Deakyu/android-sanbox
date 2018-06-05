package com.example.deakyu.refactortomvvm;

import com.example.deakyu.refactortomvvm.model.BookSearchResult;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface GoogleBooksService {
    @GET("books/v1/volumes")
    Observable<BookSearchResult> search(@Query("q") String search);
}
