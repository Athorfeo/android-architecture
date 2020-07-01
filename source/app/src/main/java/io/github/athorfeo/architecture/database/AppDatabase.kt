package io.github.athorfeo.architecture.database

import androidx.room.Database
import androidx.room.RoomDatabase
import io.github.athorfeo.architecture.database.dao.MovieDao
import io.github.athorfeo.architecture.database.entity.MovieEntity

@Database(
    entities = [
        MovieEntity::class
    ],
    version = 1,
    exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun movieDao(): MovieDao
}