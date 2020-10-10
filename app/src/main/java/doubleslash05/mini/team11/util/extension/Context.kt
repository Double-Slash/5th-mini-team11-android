package doubleslash05.mini.team11.util.extension

import android.app.Activity
import android.content.Context
import android.os.Build
import android.util.DisplayMetrics
import doubleslash05.mini.team11.BuildConfig
import kotlin.math.roundToInt

fun Context.dpToPx(dp: Int): Int {
    return dpToPx(dp.toFloat()).roundToInt()
}

fun Context.dpToPx(dp: Float): Float {
    val metrics = resources.displayMetrics
    return (dp * (metrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT))
}

fun Context.pxToDp(px: Float): Int {
    val metrics = resources.displayMetrics
    return (px / (metrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)).roundToInt()
}

fun Context.getScreenSize() : Pair<Int, Int> {
    val screenWidth: Int
    val screenHeight: Int
    if (BuildConfig.VERSION_CODE >= Build.VERSION_CODES.R) {
        val screenRect = (this as Activity).windowManager.currentWindowMetrics.bounds
        screenWidth = screenRect.width()
        screenHeight = screenRect.height()
    } else {
        screenWidth = display!!.width
        screenHeight = display!!.height
    }

    return Pair(screenWidth, screenHeight)
}
