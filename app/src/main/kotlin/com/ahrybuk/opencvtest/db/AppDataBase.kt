package com.ahrybuk.opencvtest.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ahrybuk.opencvtest.db.daos.SessionDao
import com.ahrybuk.opencvtest.db.entities.SessionEntity

@Database(entities = [SessionEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun sessionDao(): SessionDao
}