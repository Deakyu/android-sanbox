package com.example.lee52.observerapi.observer

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class BobTest {

    private lateinit var SUT: Bob

    @Before
    fun setup() {
        SUT = Bob()
    }

    @Test
    fun test1() {
        SUT.addObserver(Angeline())
        SUT.addObserver(Tina())
        SUT.cookBurger("'It takes bun to know Bun Burger'")
    }

}