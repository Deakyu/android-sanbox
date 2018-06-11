package com.example.deakyu.refactortomvp;

import java.util.List;

public interface BooksPresenter {
    void bind(BooksView view);
    void unbind();
    void performSearch(String userInput);
    List<Book> getCurrentBookList();
}
