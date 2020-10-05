package doubleslash05.mini.team11.util

import android.content.Context

class SharedPreferenceUtil(private val context: Context) {
    private val prefs by lazy {
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    var isShowVoiceGuide: Boolean
        get() = prefs.getBoolean(PREFS_IS_SHOW_VOICE_GUIDE, true)
        set(value) = prefs.edit().putBoolean(PREFS_IS_SHOW_VOICE_GUIDE, value).apply()

    companion object {
        private const val PREFS_NAME = "DoubleSlash05_mini_team11"

        private const val PREFS_IS_SHOW_VOICE_GUIDE = "PREFS_IS_SHOW_VOICE_GUIDE"
    }
}