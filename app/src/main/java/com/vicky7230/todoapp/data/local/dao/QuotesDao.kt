package com.vicky7230.todoapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.vicky7230.todoapp.data.local.entity.QuoteEntity
import com.vicky7230.todoapp.utils.Quote
import kotlinx.coroutines.flow.Flow

@Dao
interface QuotesDao {

    @Insert
    suspend fun insertQuote(quoteEntity: QuoteEntity)

    @Update
    suspend fun updateQuote(quoteEntity: QuoteEntity)

    @Query("DELETE FROM quotes")
    suspend fun delete()

    @Query("SELECT * FROM quotes")
    fun getQuotes(): Flow<List<QuoteEntity>>

    @Query("SELECT * FROM quotes where id = :quoteId")
    suspend fun getQuoteById(quoteId: Int): Quote
}