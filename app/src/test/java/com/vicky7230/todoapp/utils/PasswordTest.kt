package com.vicky7230.todoapp.utils

import org.junit.Assert.*

import org.junit.Test

class PasswordTest {

    @Test
    fun isValidPassword_blankInput_expectedRequiredField() {
        //Arrange
        val helper = Helper()
        //Act
        val result = helper.isValidPassword("")
        //Assert
        assertEquals("Password cannot be empty", result)
    }


    @Test
    fun isValidPassword_2chatInput_expectedMinimumLength() {
        //Arrange
        val helper = Helper()
        //Act
        val result = helper.isValidPassword("ab")
        //Assert
        assertEquals("Minimum password length should be 6", result)
    }


    @Test
    fun isValidPassword_2chatInput_expectedValidPassword() {
        //Arrange
        val helper = Helper()
        //Act
        val result = helper.isValidPassword("Password123")
        //Assert
        assertEquals("Valid", result)
    }
}