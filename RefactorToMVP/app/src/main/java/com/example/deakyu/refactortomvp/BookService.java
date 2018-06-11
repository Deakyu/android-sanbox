package com.example.deakyu.refactortomvp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface BookService {
    @GET("books")
    Call<List<Book>> search(@Query("search") String search);
}
