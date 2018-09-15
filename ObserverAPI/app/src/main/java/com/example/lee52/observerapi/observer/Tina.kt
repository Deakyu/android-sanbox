package com.example.lee52.observerapi.observer

import java.util.*

class Tina: Observer {

    val name = "Tina"

    override fun update(o: Observable?, arg: Any?) {
        when(o) {
            is Bob -> {
                if(arg is Burger) {
                    println("$name is serving ${arg.name}")
                }
            }
            else -> println(o?.javaClass.toString())
        }
    }
}