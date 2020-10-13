package doubleslash05.mini.team11

import android.app.Application
import doubleslash05.mini.team11.util.SharedPreferenceUtil

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        prefs = SharedPreferenceUtil(this)
    }

    companion object {
        lateinit var prefs : SharedPreferenceUtil
            private set
    }
}