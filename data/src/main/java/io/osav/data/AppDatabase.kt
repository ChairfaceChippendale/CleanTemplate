package io.osav.data

import androidx.room.Database
import androidx.room.RoomDatabase
import io.osav.data.cats.local.CatDao
import io.osav.data.cats.local.model.CatRoom

@Database(
    version = 1,
    exportSchema = false,
    entities = [
        CatRoom::class
//        BirdRoom::class
    ])
abstract class AppDatabase: RoomDatabase() {
    abstract fun getCatDao(): CatDao
//    abstract fun getBirdDao(): BirdDao
}