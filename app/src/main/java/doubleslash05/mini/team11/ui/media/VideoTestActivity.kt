package doubleslash05.mini.team11.ui.media

import android.net.Uri
import android.os.Bundle
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


//        val uri = Uri.parse("https://vt.tumblr.com/tumblr_o600t8hzf51qcbnq0_480.mp4") // 서버에 있는 영상
//        val uri = Uri.parse("http://techslides.com/demos/sample-videos/small.mp4") // 서버에 있는 영상
//        Uri.parse(Environment.getExternalStorageDirectory().getAbsolutePath()+"filename") // SD card 영상
        val uri = Uri.parse("android.resource://$packageName/${R.raw.out}")
        videoview_video.setVideoURI(uri)

        videoview_video.setOnPreparedListener {
            Log.d("TEST", "Done")
            Toast.makeText(this@VideoTestActivity, "Done!", Toast.LENGTH_LONG)
            videoview_video.seekTo(1)

//            videoview_video.start()
        }

        button_video_test.setOnClickListener {
            videoview_video.pause()
            videoview_video.seekTo(videoview_video.currentPosition + 500)
            Log.d("TEST", (videoview_video.currentPosition).toString())
        }

        button_video_resume.setOnClickListener {
            if (videoview_video.isPlaying) {
                videoview_video.pause()
            } else {
                videoview_video.start()
            }
            Log.d("TEST", (videoview_video.currentPosition).toString())
        }
    }
}