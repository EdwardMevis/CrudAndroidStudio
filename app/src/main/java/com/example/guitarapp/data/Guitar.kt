package com.example.guitarapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "guitars")
data class Guitar(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val price: Double,
    val quantity: Int
)