package com.example.guitarapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Guitar::class], version = 1, exportSchema = false)
abstract class DBase : RoomDatabase() {

    abstract fun GuitarDao(): GuitarDAO

    companion object {
        @Volatile
        private var Instance: DBase? = null

        fun getDatabase(context: Context): DBase {
            // if the Instance is not null, return it, otherwise create a new database instance.
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, DBase::class.java, "guitar_database")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }
    }

}