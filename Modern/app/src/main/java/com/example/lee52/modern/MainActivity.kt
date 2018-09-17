package com.example.lee52.modern

import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.lee52.modern.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.apply {
            repositoryName.text = "Medium Android Repository Article"
            repositoryOwner.text = "Mladen Rakonjac"
            numberOfStarts.text = "1000 stars"
        }

        var repository = Repository("Medium Android Repository Article", "Mladen Rakonjac", 1000, true)
        binding.repository = repository
        binding.executePendingBindings()

        Handler().postDelayed({repository.repositoryName = "New Name"}, 2000)
    }
}
