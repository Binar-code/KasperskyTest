package com.example.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.local.entity.WordEntity

@Dao
interface WordDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(entity: WordEntity): Long

    @Query("UPDATE words SET isStarred = :star WHERE english = :eng")
    suspend fun setStarred(eng: String, star: Boolean): Int

    @Query("DELETE FROM words WHERE english = :english")
    suspend fun deleteByEnglish(english: String): Int

    @Query("SELECT * FROM words ORDER BY id DESC")
    fun pagingAll(): PagingSource<Int, WordEntity>

    @Query("SELECT * FROM words WHERE isStarred = 1 ORDER BY id DESC")
    fun pagingStarred(): PagingSource<Int, WordEntity>
}
