package doubleslash05.mini.team11.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import doubleslash05.mini.team11.ui.media.VideoTestActivity

class SplashActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        startActivity(Intent(this, VideoTestActivity::class.java))
        finish()
    }
}