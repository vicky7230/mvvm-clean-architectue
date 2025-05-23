package com.vicky7230.todoapp.utils

import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.unmockkAll
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test

class UserServiceTest {

    private lateinit var userRepository: UserRepository

    @Before
    fun setUp() {
        userRepository = mockk()
        every { userRepository.loginUser(any(), any()) } returns LOGIN_STATUS.INVALID_PASSWORD
    }

    @After
    fun tearDown() {
        clearAllMocks()
        unmockkAll()
    }

    @Test
    fun testUserService() {
        //Arrange
        val sut = UserService(userRepository)
        //Act
        val result = sut.loginUser("abc@gmail.com", "123ert")
        //Assert
        assertEquals("Password is invalid", result)
    }
}