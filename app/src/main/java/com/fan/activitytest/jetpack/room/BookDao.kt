package com.fan.activitytest.jetpack.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface BookDao {

    @Insert
    fun insertBook(book: BookEntity): Long

    @Query("select * from BookEntity")
    fun loadAllBooks(): List<BookEntity>

}