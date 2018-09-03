package com.example.deakyu.mediatordemo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    val mediator = ChatMediator()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val user1: IUser = User(mediator, "Deakyu")
        val user2: IUser = User(mediator, "Songyi")
        val user3: IUser = User(mediator, "Eunbi")

        mediator.addUser(user1)
        mediator.addUser(user2)
        mediator.addUser(user3)

        user1.send("Let's drink tonight!")

    }
}
