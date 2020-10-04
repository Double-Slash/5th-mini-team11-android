package doubleslash05.mini.team11.util.extension

import android.content.Context
import android.util.DisplayMetrics
import kotlin.math.roundToInt

fun Context.dpToPx(dp: Float): Int {
    val metrics = resources.displayMetrics
    return (dp * (metrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)).roundToInt()
}


fun Context.pxToDp(px: Float): Int {
    val metrics = resources.displayMetrics
    return (px / (metrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)).roundToInt()
}

