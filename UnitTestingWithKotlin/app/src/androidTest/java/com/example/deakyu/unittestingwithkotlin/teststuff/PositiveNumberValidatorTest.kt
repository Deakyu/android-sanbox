package com.example.deakyu.unittestingwithkotlin.teststuff

import org.junit.Assert
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class PositiveNumberValidatorTest {

    lateinit var SUT: PositiveNumberValidator

    @Before
    fun setup() {
        SUT = PositiveNumberValidator()
    }

    @Test
    fun test1() {
        var result = SUT.isPositive(-1)
        Assert.assertEquals(result, false)
    }

}