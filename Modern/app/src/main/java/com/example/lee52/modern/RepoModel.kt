package com.example.lee52.modern

import android.os.Handler

class RepoModel {
    fun refreshData(onDataReadyCallback: OnDataReadyCallback) {
        Handler().postDelayed({ onDataReadyCallback.onDataReady("new data") }, 2000)
    }

    fun getRepositories(onRepositoryReadyCallback: OnRepositoryReadyCallback) {
        var arrayList = ArrayList<Repository>().apply {
            add(Repository("First", "Owner 1", 100, false))
            add(Repository("Second", "Owner 2", 30, true))
            add(Repository("Third", "Owner 3", 430, false))
        }

        Handler().postDelayed({onRepositoryReadyCallback.onDataReady(arrayList)}, 2000)
    }
}

interface OnDataReadyCallback {
    fun onDataReady(data: String)
}

interface OnRepositoryReadyCallback {
    fun onDataReady(data: ArrayList<Repository>)
}