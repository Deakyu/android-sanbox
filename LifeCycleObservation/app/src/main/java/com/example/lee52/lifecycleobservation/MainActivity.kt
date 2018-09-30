package com.example.lee52.lifecycleobservation

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("DEAKYULEE", "onCreate MainActivity")

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.main_activity_button).setOnClickListener {
            val intent = Intent(MainActivity@this, NextActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("DEAKYULEE", "onStart MainActivity")
    }

    override fun onResume() {
        super.onResume()
        Log.d("DEAKYULEE", "onResume MainActivity")
    }

    override fun onPause() {
        super.onPause()
        Log.d("DEAKYULEE", "onPause MainActivity")
    }

    override fun onStop() {
        super.onStop()
        Log.d("DEAKYULEE", "onStop MainActivity")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("DEAKYULEE", "onDestroy MainActivity")
    }
}
