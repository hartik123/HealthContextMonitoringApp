package com.example.contextmonitoring

//changed
import android.content.res.AssetFileDescriptor

import android.content.ContentResolver
import android.graphics.Bitmap
import android.graphics.Color
import android.media.MediaMetadataRetriever
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.FileNotFoundException
import kotlin.math.min

suspend fun heartRateCalculator(uri: Uri, contentResolver: ContentResolver): Int {
    return withContext(Dispatchers.IO) {
        val result: Int
        val retriever = MediaMetadataRetriever()
        val frameList = ArrayList<Bitmap>()

        try {
            val assetFileDescriptor: AssetFileDescriptor = contentResolver.openAssetFileDescriptor(uri, "r")
                ?: throw FileNotFoundException("Cannot open file in assets.")

            retriever.setDataSource(assetFileDescriptor.fileDescriptor)

            val frameCount = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_VIDEO_FRAME_COUNT)?.toIntOrNull() ?: 0

            if (frameCount == 0) {
                Log.e("HeartRateCalculator", "No frame count detected")
                return@withContext -1
            }

            var i = 0
            while (i < frameCount) {
                try {
                    val bitmap = retriever.getFrameAtIndex(i)
                    if (bitmap != null) {
                        frameList.add(bitmap)  // Add only non-null frames
                    } else {
                        Log.e("HeartRateCalculator", "Skipping null frame at index $i")
                    }
                } catch (e: Exception) {
                    Log.e("HeartRateCalculator", "Error retrieving frame at index $i: ${e.message}")
                }
                i += 50
            }

        } catch (e: Exception) {
            Log.e("HeartRateCalculator", "Error retrieving frames: ${e.message}")
        } finally {
            retriever.release()

            if (frameList.isEmpty()) {
                Log.e("HeartRateCalculator", "No frames were extracted from the video")
                return@withContext -1
            }

            var redBucket: Long
            var pixelCount: Long = 0
            val a = mutableListOf<Long>()
            for (i in frameList) {
                redBucket = 0
                for (y in 350 until 450) {
                    for (x in 350 until 450) {
                        val c: Int = i.getPixel(x, y)
                        pixelCount++
                        redBucket += Color.red(c) + Color.blue(c) + Color.green(c)
                    }
                }
                a.add(redBucket)
            }
            val b = mutableListOf<Long>()
            for (i in 0 until a.lastIndex - 5) {
                val temp =
                    (a.elementAt(i) + a.elementAt(i + 1) + a.elementAt(i + 2)
                            + a.elementAt(
                        i + 3
                    ) + a.elementAt(
                        i + 4
                    )) / 4
                b.add(temp)
            }
            var x = b.elementAt(0)
            var count = 0
            for (i in 1 until b.lastIndex) {
                val p = b.elementAt(i)
                if ((p - x) > 3500) {
                    count += 1
                }
                x = b.elementAt(i)
            }
            val rate = ((count.toFloat()) * 60).toInt()
            result = (rate / 4)
        }
        result
    }
}