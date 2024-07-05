package com.example.squadzipexam.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

/**
 * Created by Ivan Esguerra on 7/3/2024.
 **/
@Database(
    entities = [PersonEntity::class],
    version = 1
)
abstract class PersonDatabase: RoomDatabase() {
    abstract fun getPersonDao(): PersonDao
}