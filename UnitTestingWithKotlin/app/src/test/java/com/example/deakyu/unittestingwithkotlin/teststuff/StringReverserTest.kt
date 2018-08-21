package com.example.deakyu.unittestingwithkotlin.teststuff

import org.junit.Assert
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.hamcrest.CoreMatchers.`is` as Is

class StringReverserTest {

    lateinit var SUT: StringReverser

    @Before
    fun setUp() {
        SUT = StringReverser()
    }

    @Test
    fun reverse_emptyString_emptyStringReturned() {
        val result = SUT.reverseString("")
        assertThat(result, Is(""))
    }

    @Test
    fun reverse_singleCharacter_sameStringReturned() {
        val result = SUT.reverseString("a")
        assertThat(result, Is("a"))
    }

    @Test
    fun reverse_longString_reversedStringReturned() {
        val result = SUT.reverseString("deakyu")
        assertThat(result, Is("uykaed"))
    }
}