package com.ahrybuk.opencvtest.di

import androidx.room.Room
import com.ahrybuk.opencvtest.data.repositories.base.SessionRepository
import com.ahrybuk.opencvtest.data.repositories.db.SessionsDBRepository
import com.ahrybuk.opencvtest.db.AppDatabase
import com.ahrybuk.opencvtest.utils.ImageUtil
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module


val appModule = module {
    single {
        Room.databaseBuilder(
            get(),
            AppDatabase::class.java, "appDatabase"
        ).build()
    }

    factory { ImageUtil(androidContext()) }

    factory<SessionRepository> { SessionsDBRepository(get(), get()) }
}