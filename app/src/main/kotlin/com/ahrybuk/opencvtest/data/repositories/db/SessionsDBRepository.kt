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
import io.reactivex.Observable
import org.koin.core.KoinComponent
import org.koin.core.inject
import java.io.*


class SessionsDBRepository : SessionRepository, KoinComponent {

    private val appDatabase: AppDatabase by inject()
    private val context: Context by inject()

    override fun getSession(timeStamp: Long): Observable<Session> {
        return appDatabase.sessionDao().getSingleByTimestamp(timeStamp).map { entity ->
            Session(
                pathToBitmap(entity.mainImagePath)!!,
                pathToBitmap(entity.faceDetectedImagePath)!!,
                entity.timestamp
            )
        }
    }

    override fun saveSession(session: Session): Observable<Boolean> {
        return Observable.fromCallable {
            appDatabase.sessionDao().insert(
                SessionEntity(
                    session.timestamp,
                    bitmapToPath(session.mainImage, session.timestamp)!!,
                    bitmapToPath(session.faceDetectedImage, session.timestamp)!!
                )
            ) > 0
        }
    }

    override fun getSessionsList(from: Int, step: Int): Observable<List<Session>> {
        return appDatabase.sessionDao().getAll().map {
            it.map { entity ->
                Session(
                    pathToBitmap(entity.mainImagePath)!!,
                    pathToBitmap(entity.faceDetectedImagePath)!!,
                    entity.timestamp
                )
            }
        }
    }

    private fun bitmapToPath(bitmapImage: Bitmap, timeStamp: Long): String? {
//        TODO check
        val cw = ContextWrapper(context)
        // path to /data/data/yourapp/app_data/imageDir
        val directory = cw.getDir("images", MODE_PRIVATE)
        // Create imageDir
        val mypath = File(directory, "$timeStamp.jpg")
        var fos: FileOutputStream? = null
        try {
            fos = FileOutputStream(mypath)
            // Use the compress method on the BitMap object to write image to the OutputStream
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos)
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            try {
                fos!!.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        return directory.absolutePath
    }

    private fun pathToBitmap(path: String): Bitmap? {
//        TODO check
        try {
            val f = File(path, "profile.jpg")
            return BitmapFactory.decodeStream(FileInputStream(f))
        } catch (e: FileNotFoundException) {
            return null
        }
    }
}