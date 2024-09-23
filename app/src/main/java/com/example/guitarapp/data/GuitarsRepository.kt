package com.example.guitarapp.data

import kotlinx.coroutines.flow.Flow

interface GuitarsRepository{
    fun getAllGuitarsStream(): Flow<List<Guitar>>

    fun getGuitarStream(id: Int): Flow<Guitar?>

    suspend fun insertGuitar(guitar: Guitar)

    suspend fun deleteGuitar(guitar: Guitar)

    suspend fun updateGuitar(guitar: Guitar)
}