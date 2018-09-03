package com.example.deakyu.mediatordemo

interface IChatMediator {
    fun sendMessage(msg: String, user: IUser)

    fun addUser(user: IUser)
}