package com.example.guitarapp.data

import kotlinx.coroutines.flow.Flow

class OfflineGuitarsRepository(private val guitarDAO: GuitarDAO) : GuitarsRepository{
    override fun getAllGuitarsStream(): Flow<List<Guitar>> = guitarDAO.getAllGuitars()

    override fun getGuitarStream(id: Int): Flow<Guitar?> = guitarDAO.getGuitar(id)

    override suspend fun insertGuitar(guitar: Guitar) = guitarDAO.insert(guitar)

    override suspend fun deleteGuitar(guitar: Guitar) = guitarDAO.delete(guitar)

    override suspend fun updateGuitar(guitar: Guitar) = guitarDAO.update(guitar)
}