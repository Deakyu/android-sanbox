package com.example.deakyu.mediatordemo

interface IUser {
    fun send(msg: String)

    fun receive(msg: String)
}