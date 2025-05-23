package com.vicky7230.todoapp

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.vicky7230.todoapp.data.local.ProductsDb
import com.vicky7230.todoapp.data.local.dao.QuotesDao
import com.vicky7230.todoapp.data.local.entity.QuoteEntity
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class QuotesDaoTest {

    lateinit var productsDb: ProductsDb
    lateinit var quotesDao: QuotesDao

    @Before
    fun setUp() {
        productsDb = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            ProductsDb::class.java
        ).allowMainThreadQueries().build()

        quotesDao = productsDb.getQuotesDao()
    }

    @Test
    fun insertQuote_expectedSingleQuote() = runTest {
        //Arrange
        val quoteEntity = QuoteEntity(0, "This is a test quote", "Vipin")
        //Act
        quotesDao.insertQuote(quoteEntity)
        val quotesList = quotesDao.getQuotes().first()
        //Assert
        assertEquals(1, quotesList.size)
        assertEquals("This is a test quote", quotesList[0].text)
        assertEquals("Vipin", quotesList[0].author)
    }

    @Test
    fun deleteQuote_expectedNoResult() = runTest {
        //Arrange
        val quoteEntity = QuoteEntity(0, "This is a test quote", "Vipin")
        quotesDao.insertQuote(quoteEntity)
        //Act
        quotesDao.delete()
        val quotesList = quotesDao.getQuotes().first()
        //Assert
        assertEquals(0, quotesList.size)
    }

    @After
    fun tearDown() {
        productsDb.close()
    }
}