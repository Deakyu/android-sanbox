package com.example.deakyu.refreshwithnetworkcall

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var refreshServiceIntent: Intent

    override fun onClick(v: View?) {
        if(v?.id == R.id.destroy_button) {
            this@MainActivity.stopService(refreshServiceIntent)
        } else if(v?.id == R.id.go_to_another_activity_button) {
            startActivity(Intent(this@MainActivity, Main2Activity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        refreshServiceIntent = Intent(applicationContext, RefreshService::class.java)

        findViewById<Button>(R.id.destroy_button).setOnClickListener(this)
        findViewById<Button>(R.id.go_to_another_activity_button).setOnClickListener(this)

        startService(refreshServiceIntent)

    }
}
