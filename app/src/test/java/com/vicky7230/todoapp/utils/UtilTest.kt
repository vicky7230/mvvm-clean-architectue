package com.vicky7230.todoapp.utils

import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Assert.*
import org.junit.Test

class UtilTest {

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @Test
    fun testGetUser() {
        val sut = Util(mainCoroutineRule.testDispatcher)
        runTest {
            sut.getAddressDetail()
            mainCoroutineRule.testDispatcher.scheduler.advanceUntilIdle()
            assertEquals(true, sut.globalArg)
        }
    }
}