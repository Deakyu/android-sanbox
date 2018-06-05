package com.example.deakyu.refactortomvvm;

import com.example.deakyu.refactortomvvm.model.BookSearchResult;

import rx.Observable;
import rx.Scheduler;

public class BooksViewModel {

    private BooksInteractor interactor;
    private Scheduler scheduler;

    public BooksViewModel(BooksInteractor interactor, Scheduler scheduler) {
        this.interactor = interactor;
        this.scheduler = scheduler;
    }

    public Observable<BookSearchResult> search(String search) {
        return interactor.search(search).observeOn(scheduler);
    }

}
