package com.mauriciotogneri.greencoffee

import android.app.Activity
import android.graphics.Bitmap
import android.graphics.Bitmap.CompressFormat
import android.support.test.InstrumentationRegistry
import android.support.test.runner.lifecycle.ActivityLifecycleMonitorRegistry
import android.support.test.runner.lifecycle.Stage
import java.io.File
import java.io.FileOutputStream

internal class ScreenCapture {
  fun takeScreenshot(file: File) {
    InstrumentationRegistry.getInstrumentation().runOnMainSync {
      val resumedActivities = ActivityLifecycleMonitorRegistry.getInstance().getActivitiesInStage(Stage.RESUMED)
      val iterator = resumedActivities.iterator()
      if (iterator.hasNext()) {
        val activity = iterator.next()
        try {
          takeScreenshot(activity, file)
        } catch (e: Exception) {
          // ignore
        }
      }
    }
  }

  @Throws(Exception::class)
  private fun takeScreenshot(activity: Activity, file: File) {
    val view = activity.window.decorView.rootView
    view.isDrawingCacheEnabled = true
    view.buildDrawingCache(true)
    val bitmap = Bitmap.createBitmap(view.drawingCache)
    view.isDrawingCacheEnabled = false

    val parentFolder = file.parentFile
    if (parentFolder.exists() || parentFolder.mkdirs()) {
      val outputStream = FileOutputStream(file)
      bitmap.compress(CompressFormat.JPEG, 90, outputStream)
      outputStream.flush()
      outputStream.close()
    }
  }
}