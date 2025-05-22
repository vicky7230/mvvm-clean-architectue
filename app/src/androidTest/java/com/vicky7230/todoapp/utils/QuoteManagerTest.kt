package com.vicky7230.todoapp.utils

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import kotlinx.serialization.SerializationException
import okio.FileNotFoundException
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test

class QuoteManagerTest {

    @Test(expected = FileNotFoundException::class)
    fun populateQuoteFromAssets() {
        //Arrange
        val quoteManager = QuoteManager()
        val context = ApplicationProvider.getApplicationContext<Context>()

        //Act
        quoteManager.populateQuoteFromAssets(context, "")
    }

    @Test(expected = SerializationException::class)
    fun test_populateQuoteFromAssets_InvalidJSON_expected_Exception() {
        //Arrange
        val quoteManager = QuoteManager()
        val context = ApplicationProvider.getApplicationContext<Context>()

        //Act
        quoteManager.populateQuoteFromAssets(context, "malformed.json")
    }

    @Test(expected = SerializationException::class)
    fun test_populateQuoteFromAssets_ValidJSON_expected_Count() {
        //Arrange
        val quoteManager = QuoteManager()
        val context = ApplicationProvider.getApplicationContext<Context>()

        //Act
        quoteManager.populateQuoteFromAssets(context, "quotes.json")

        //Assert
        assertEquals(6, quoteManager.quoteList.size)
    }

    @Test
    fun testPreviousQuote_expected_CorrectQuote() {
        //Arrange
        val quoteManager = QuoteManager()
        quoteManager.populateQuotes(arrayOf(
            Quote("This is first quote", "1"),
            Quote("This is second quote", "2"),
            Quote("This is third quote", "3"),
        ))

        //Act
        val quote = quoteManager.getPreviousQuote()

        //Assert
        assertEquals("1", quote.author)
    }

    @Test
    fun testNextQuote_expected_CorrectQuote() {
        //Arrange
        val quoteManager = QuoteManager()
        quoteManager.populateQuotes(arrayOf(
            Quote("This is first quote", "1"),
            Quote("This is second quote", "2"),
            Quote("This is third quote", "3"),
        ))

        //Act
        val quote = quoteManager.getNextQuote()

        //Assert
        assertEquals("2", quote.author)
    }
}