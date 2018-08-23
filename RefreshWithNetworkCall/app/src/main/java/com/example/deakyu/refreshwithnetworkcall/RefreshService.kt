package com.example.deakyu.refreshwithnetworkcall

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import java.util.*
import kotlin.concurrent.schedule

class RefreshService : Service() {
    override fun onBind(intent: Intent?): IBinder? = null

    private val timer = Timer()

    override fun onCreate() {
        super.onCreate()
        timer.scheduleAtFixedRate(object: TimerTask() {
            override fun run() {
                sendRequestToServer()
            }
        }, 0, 1*5*1000)
    }

    override fun onDestroy() {
        timer.cancel()
        Log.d("SERVICE", "Service DESTROYED!")
        super.onDestroy()
    }

    private fun sendRequestToServer() {
        Timer("Simulation", false).schedule(1000) {
            Log.d("SERVICE", "Simulation executed!")
        }
    }
}