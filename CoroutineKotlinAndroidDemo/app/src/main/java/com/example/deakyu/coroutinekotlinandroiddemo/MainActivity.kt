package com.example.deakyu.coroutinekotlinandroiddemo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.experimental.CoroutineCallAdapterFactory
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var retrofit: Retrofit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        retrofit = Retrofit.Builder()
                .baseUrl("https://5b86296505c5890014a90985.mockapi.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .build()

        val service = retrofit.create(UserService::class.java)
        launch{
            val response = service.getUsers().await()
            if(response.isSuccessful) {
                val users = response.body()
                withContext(UI) {
                    findViewById<TextView>(R.id.tv_username).text = users?.get(0)?.name
                }
                Log.d("COROUTINE", "${users}")
            } else {
                val error = response.message()
            }

        }
    }
}
