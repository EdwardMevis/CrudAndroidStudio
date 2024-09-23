package com.example.guitarapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface GuitarDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(guitar: Guitar)

    @Update
    suspend fun update(guitar: Guitar)

    @Delete
    suspend fun delete(guitar: Guitar)

    @Query("SELECT * from guitars WHERE id = :id")
    fun getGuitar(id: Int): Flow<Guitar>

    @Query("SELECT * from guitars ORDER BY name ASC")
    fun getAllGuitars(): Flow<List<Guitar>>

}