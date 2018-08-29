package com.example.deakyu.coroutinekotlinandroiddemo

import kotlinx.coroutines.experimental.Deferred
import retrofit2.Response
import retrofit2.http.GET

interface UserService {
    @GET("users")
    fun getUsers(): Deferred<Response<List<User>>>
}