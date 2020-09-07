package doubleslash05.mini.team11.ui.media

import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.widget.MediaController
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import doubleslash05.mini.team11.R
import kotlinx.android.synthetic.main.activity_video_player.*

class VideoTestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_player)

        val mediaController = MediaController(this)
        videoview_video.setMediaController(mediaController)

//         val uri = Uri.parse("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerBlazes.mp4") // 서버에 있는 영상
//        Uri.parse(Environment.getExternalStorageDirectory().getAbsolutePath()+"filename") // SD card 영상
        val uri = Uri.parse("android.resource://$packageName/${R.raw.test_video}")
        videoview_video.setVideoURI(uri)

        videoview_video.setOnPreparedListener {
            Log.d("TEST", "Done")
            Toast.makeText(this@VideoTestActivity, "Done!", Toast.LENGTH_LONG)
            videoview_video.start()
        }

        button_video_test.setOnClickListener {
            videoview_video.pause()
            videoview_video.seekTo(8000)
        }

        button_video_resume.setOnClickListener {
            videoview_video.resume()
        }

    }
}