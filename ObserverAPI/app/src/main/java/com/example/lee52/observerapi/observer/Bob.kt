package com.example.lee52.observerapi.observer

import java.util.*

class Bob: Observable() {
    val name = "Bob"
    fun cookBurger(name: String) {
        val burger = Burger(name)

        setChanged()
        notifyObservers(burger)
    }
}