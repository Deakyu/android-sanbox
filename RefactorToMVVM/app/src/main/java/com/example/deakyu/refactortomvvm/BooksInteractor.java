package com.example.deakyu.refactortomvvm;

import com.example.deakyu.refactortomvvm.model.BookSearchResult;

import rx.Observable;

public interface BooksInteractor {

    Observable<BookSearchResult> search(String search);

}
