package doubleslash05.mini.team11.ui.common.widget.recipevideo

import android.content.Context
import android.media.MediaPlayer
import android.media.MediaPlayer.OnPreparedListener
import android.util.AttributeSet
import android.view.View
import android.view.View.OnClickListener
import android.widget.FrameLayout
import android.widget.MediaController.MediaPlayerControl
import android.widget.SeekBar
import doubleslash05.mini.team11.R
import doubleslash05.mini.team11.model.data.RecipeVideoData
import kotlinx.android.synthetic.main.view_recipe_video.view.*


class RecipeVideoView(context: Context, attrs: AttributeSet?, defStyle: Int) : FrameLayout(context, attrs, defStyle), OnPreparedListener, MediaPlayerControl, SeekBar.OnSeekBarChangeListener {

    private val controller = BasicMediaController(context)
    private lateinit var data: RecipeVideoData

    val seekBarthread = Thread(Runnable {
        while (true) {
            if (player_recipevideo.isPlaying) {
                handler.post(Runnable { seekbar_recipevideo.progress = player_recipevideo.currentPosition })
            }
            try {
                Thread.sleep(100)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        }
    })

    constructor(context: Context) : this(context, null, 0)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    init {
        val v = View.inflate(context, R.layout.view_recipe_video, this)

        controller.setAnchorView(player_recipevideo)
        controller.setMediaPlayer(player_recipevideo)

        seekbar_recipevideo.isEnabled = false
        button_recipevideo.isEnabled = false

        // player_recipevideo
        player_recipevideo.setOnPreparedListener(this)

        seekbar_recipevideo.setOnSeekBarChangeListener(this)

        button_recipevideo.setOnClickListener(OnClickListener {
            if (player_recipevideo.isPlaying) {
                pause()
            } else {
                start()
            }
        })

        player_recipevideo.setOnClickListener(OnClickListener {
            //                controller.show(3000);
        })
    }

    private var trackingStart = false
    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
        if (!fromUser) return
        player_recipevideo.seekTo(progress)
    }

    override fun onStartTrackingTouch(seekBar: SeekBar?) {
        trackingStart = player_recipevideo.isPlaying
        player_recipevideo.pause()
    }

    override fun onStopTrackingTouch(seekBar: SeekBar?) {
        if (trackingStart) player_recipevideo.start()
    }

    override fun onPrepared(mp: MediaPlayer) {
        seekbar_recipevideo.max = mp.duration
        seekbar_recipevideo.isEnabled = true
        button_recipevideo.isEnabled = true
        seekBarthread.start()
    }

    override fun start() {
        player_recipevideo.start()
    }

    override fun pause() {
        player_recipevideo.pause()
    }

    override fun getDuration(): Int {
        return player_recipevideo.duration
    }

    override fun getCurrentPosition(): Int {
        return player_recipevideo.currentPosition
    }

    override fun seekTo(pos: Int) {
        player_recipevideo.seekTo(pos)
    }

    override fun isPlaying(): Boolean {
        return player_recipevideo.isPlaying
    }

    override fun getBufferPercentage(): Int {
        return player_recipevideo.bufferPercentage
    }

    override fun canPause(): Boolean {
        return player_recipevideo.canPause()
    }

    override fun canSeekBackward(): Boolean {
        return player_recipevideo.canSeekBackward()
    }

    override fun canSeekForward(): Boolean {
        return player_recipevideo.canSeekForward()
    }

    override fun getAudioSessionId(): Int {
        return player_recipevideo.audioSessionId
    }

    fun setData(data: RecipeVideoData) {
        this.data = data
        player_recipevideo.setDataSource(data.path)
        seekbar_recipevideo.setSections(data.sections)
    }

}