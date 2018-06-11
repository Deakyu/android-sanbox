package com.example.deakyu.refactortomvp;

import java.util.List;

import retrofit2.Call;

public interface BooksInteractor {
    Call<List<Book>> search(String search);
}
