package com.vicky7230.todoapp.utils

import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class HelperTest {

    lateinit var helper: Helper

    @Before
    fun setUp(){
        helper = Helper()
    }

    @After
    fun tearDown(){
        println("After Every test case")
    }

    @Test
    fun isPalindrome_inputString_level_expectedTrue() {
        //Act
        val result = helper.isPalindrome("level")
        //Assert
        assertEquals(true, result)
    }

    @Test
    fun isPalindrome_inputString_hello_expectedFalse() {
        //Act
        val result = helper.isPalindrome("hello")
        //Assert
        assertEquals(false, result)
    }
}