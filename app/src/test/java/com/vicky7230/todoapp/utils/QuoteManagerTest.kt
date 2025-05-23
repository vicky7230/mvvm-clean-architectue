package com.vicky7230.todoapp.utils

import android.content.Context
import android.content.res.AssetManager
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import org.junit.After
import org.junit.Before
import org.junit.Test

class QuoteManagerTest {

    lateinit var context: Context
    lateinit var assetManager: AssetManager

    @Before
    fun setUp() {
        context = mockk()
        assetManager = mockk()
    }

    @After
    fun tearDown() {

    }

    @Test
    fun test() {
        val testStream = QuoteManagerTest::class.java.getResourceAsStream("/quotes.json")
        every { context.assets } returns assetManager
        every { context.assets.open(any()) } returns testStream

        val sut = QuoteManager()
        sut.populateQuoteFromAssets(context, "")
        val quote = sut.getCurrentQuote()
        assertEquals("The only way to do great work is to love what you do.", quote.text)
    }
}