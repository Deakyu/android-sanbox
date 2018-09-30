package com.example.lee52.multipleretrofitcall

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var button: Button
    lateinit var progressBar: ProgressBar

    private val retrofit = Retrofit.Builder()
            .baseUrl("https://5bb111d36418d70014071c27.mockapi.io/multiple/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    override fun onClick(v: View?) {
        if(v?.id == R.id.call_service_button) {
            showLoading()

            val service = retrofit.create(UserService::class.java)
            val calls = mutableListOf<Call<User>>()
            val users = mutableListOf<User?>()
            for (i in 1..10) {
                calls.add(service.getUser("$i"))
            }
            val handler = Handler()
            Thread {
                for(call in calls) {
                    println(call.request().url())
                    users.add(call.execute().body())
                }
                handler.post {
                    var result = ""
                    users.forEach {
                        result = "$result\n${it?.id}, ${it?.firstName}"
                    }

                    hideLoading()
                    Toast.makeText(this@MainActivity, result, Toast.LENGTH_SHORT).show()
                }
            }.start()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button = findViewById(R.id.call_service_button)
        progressBar = findViewById(R.id.progress_bar)

        button.setOnClickListener(this)
        progressBar.visibility = View.GONE
    }

    private fun showLoading() {
        button.visibility = View.GONE
        progressBar.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        button.visibility = View.VISIBLE
        progressBar.visibility = View.GONE
    }
}
