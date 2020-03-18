package com.ahrybuk.opencvtest.utils

import android.content.Context
import android.content.ContextWrapper
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import org.koin.core.KoinComponent
import org.koin.core.inject
import java.io.*

class ImageUtil : KoinComponent {

    private val context: Context by inject()

    fun saveBitmap(bitmapImage: Bitmap, timeStamp: Long): String? {
//        TODO check
        val cw = ContextWrapper(context)
        // path to /data/data/yourapp/app_data/imageDir
        val directory = cw.getDir("images", Context.MODE_PRIVATE)
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

    fun loadBitmap(path: String): Bitmap? {
//        TODO check
        try {
            val f = File(path, "profile.jpg")
            return BitmapFactory.decodeStream(FileInputStream(f))
        } catch (e: FileNotFoundException) {
            return null
        }
    }
}