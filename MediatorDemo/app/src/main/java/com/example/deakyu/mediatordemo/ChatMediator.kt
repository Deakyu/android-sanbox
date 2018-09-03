package com.example.deakyu.mediatordemo

class ChatMediator: IChatMediator {

    private val users: MutableList<IUser> = mutableListOf()

    override fun addUser(user: IUser) {
        this.users.add(user)
    }

    override fun sendMessage(msg: String, user: IUser) {
        this.users.forEach {
            if(it != user) {
                it.receive(msg)
            }
        }
    }
}