package com.example.lee52.lifecycleobservation

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button

class NextActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("DEAKYULEE", "onCreate NextActivity")

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_next)

        findViewById<Button>(R.id.next_activity_button).setOnClickListener {
            val intent = Intent(NextActivity@this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("DEAKYULEE", "onStart NextActivity")
    }

    override fun onResume() {
        super.onResume()
        Log.d("DEAKYULEE", "onResume NextActivity")
    }

    override fun onPause() {
        super.onPause()
        Log.d("DEAKYULEE", "onPause NextActivity")
    }

    override fun onStop() {
        super.onStop()
        Log.d("DEAKYULEE", "onStop NextActivity")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("DEAKYULEE", "onDestroy NextActivity")
    }
}
