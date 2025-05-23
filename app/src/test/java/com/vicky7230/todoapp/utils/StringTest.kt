package com.vicky7230.todoapp.utils

import org.junit.Assert.*
import org.junit.Test

class StringTest {

    @Test
    fun testStringReversal_EmptyString_expectedEmptyString() {
        //Arrange
        val helper = Helper()
        //Act
        val  result = helper.reverseString("")
        //Assert
        assertEquals("", result)
    }

    @Test
    fun testStringReversal_SingleString_expectedSingleString() {
        //Arrange
        val helper = Helper()
        //Act
        val  result = helper.reverseString("a")
        //Assert
        assertEquals("a", result)
    }

    @Test
    fun testStringReversal_ValidInput_expectedReversedString() {
        //Arrange
        val helper = Helper()
        //Act
        val  result = helper.reverseString("Vipin")
        //Assert
        assertEquals("nipiV", result)
    }

    @Test(expected = IllegalArgumentException::class)
    fun testStringReversal_NullValue_expectedException() {
        //Arrange
        val helper = Helper()
        //Act
        val  result = helper.reverseString(null)
    }
}