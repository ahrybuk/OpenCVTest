package com.ahrybuk.opencvtest.data.repositories.db

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.ContextWrapper
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.ahrybuk.opencvtest.data.models.Session
import com.ahrybuk.opencvtest.data.repositories.base.SessionRepository
import com.ahrybuk.opencvtest.db.AppDatabase
import com.ahrybuk.opencvtest.db.entities.SessionEntity
import com.ahrybuk.opencvtest.utils.ImageUtil
import io.reactivex.Observable
import org.koin.core.KoinComponent
import org.koin.core.inject
import java.io.*


class SessionsDBRepository(
    private val appDatabase: AppDatabase,
    private val imageUtil: ImageUtil
) : SessionRepository, KoinComponent {

    override fun getSession(timeStamp: Long): Observable<Session> {
        return appDatabase.sessionDao().getSingleByTimestamp(timeStamp).map { entity ->
            Session(
                imageUtil.loadBitmap(entity.mainImagePath)!!,
                imageUtil.loadBitmap(entity.faceDetectedImagePath)!!,
                entity.timestamp
            )
        }
    }

    override fun saveSession(session: Session): Observable<Boolean> {
        return Observable.fromCallable {
            appDatabase.sessionDao().insert(
                SessionEntity(
                    session.timestamp,
                    imageUtil.saveBitmap(session.mainImage, session.timestamp)!!,
                    imageUtil.saveBitmap(session.faceDetectedImage, session.timestamp)!!
                )
            ) > 0
        }
    }

    override fun getSessionsList(from: Int, step: Int): Observable<List<Session>> {
        return appDatabase.sessionDao().getAll().map {
            it.map { entity ->
                Session(
                    imageUtil.loadBitmap(entity.mainImagePath)!!,
                    imageUtil.loadBitmap(entity.faceDetectedImagePath)!!,
                    entity.timestamp
                )
            }
        }
    }
}