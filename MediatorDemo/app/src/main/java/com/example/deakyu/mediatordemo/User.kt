package com.example.deakyu.mediatordemo

import android.util.Log

class User(val mediator: IChatMediator,
           val name: String): IUser {

    override fun send(msg: String) {
        Log.d("MEDIATOR", "$name -> $msg")
        mediator.sendMessage(msg, this)
    }

    override fun receive(msg: String) {
        Log.d("MEDIATOR", "$name <- $msg")
    }
}