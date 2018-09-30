package com.example.lee52.modern

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField

class MainViewModel: ViewModel() {
    var repoModel: RepoModel = RepoModel()
    var repositories = ArrayList<Repository>()
    val text = ObservableField("old data")
    val isLoading = ObservableField(false)

    fun refresh() {
        isLoading.set(true)
        repoModel.refreshData(object :OnDataReadyCallback {
            override fun onDataReady(data: String) {
                isLoading.set(false)
                text.set(data)
            }
        })
    }

    fun loadRepositories() {
        isLoading.set(true)
        repoModel.getRepositories(object: OnRepositoryReadyCallback {
            override fun onDataReady(data: ArrayList<Repository>) {
                isLoading.set(false)
                repositories = data
            }
        })
    }
}