package doubleslash05.mini.team11.ui.common.widget.recipevideo

import android.app.Activity
import android.content.Context
import android.media.MediaPlayer
import android.media.MediaPlayer.OnPreparedListener
import android.media.MediaPlayer.OnVideoSizeChangedListener
import android.os.Build
import android.util.AttributeSet
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.widget.MediaController
import doubleslash05.mini.team11.BuildConfig
import doubleslash05.mini.team11.util.Log
import java.io.IOException

class SurfaceVideoView(context: Context, attrs: AttributeSet?, defStyle: Int) : SurfaceView(context, attrs, defStyle), SurfaceHolder.Callback, MediaController.MediaPlayerControl {
    private var player = MediaPlayer()
    private var state = State.IDLE
    private var onPreparedListener: OnPreparedListener? = null
    private var onReleaseListener: (() -> Unit)? = null

    constructor(context: Context) : this(context, null, 0)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    init {
        initMediaPlayer()
        holder.addCallback(this)
    }

    private var needPrepare = false
    override fun surfaceCreated(surfaceHolder: SurfaceHolder) {
        player.setDisplay(surfaceHolder)
        try {
            if (state == State.INITIALIZED) {
                player.prepare()
                state = State.PREPARED
                needPrepare = false
            } else {
                needPrepare = true
            }
        } catch (e: IOException) {
            Log.e("RecipeVideo", "URL Error", e)
        }
    }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {
        //        boolean isValidState = (mTargetState == STATE_PLAYING);
        //        boolean hasValidSize = (mVideoWidth == w && mVideoHeight == h);
        //        if (mMediaPlayer != null && isValidState && hasValidSize) {
        //            if (mSeekWhenPrepared != 0) {
        //                seekTo(mSeekWhenPrepared);
        //            }
        //            start();
        //        }
    }

    override fun surfaceDestroyed(surfaceHolder: SurfaceHolder) {
        player.release()
        state = State.IDLE
        initMediaPlayer()
        onReleaseListener?.invoke()
    }

    override fun start() {
        when (state) {
            State.PREPARED, State.STARTED, State.PAUSED -> {
                player.start()
                state = State.STARTED
            }
            else -> {
            }
        }
    }

    override fun pause() {
        when (state) {
            State.STARTED, State.PAUSED -> {
                player.pause()
                state = State.PAUSED
            }
            else -> {
            }
        }
    }

    override fun getDuration(): Int {
        when (state) {
            State.PREPARED, State.STARTED, State.PAUSED, State.STOPPED -> return player.duration
        }
        return 0
    }

    override fun getCurrentPosition(): Int {
        return player.currentPosition
    }

    override fun seekTo(pos: Int) {
        when (state) {
            State.PREPARED, State.STARTED, State.PAUSED -> player.seekTo(pos)
            else -> {
            }
        }
    }

    override fun isPlaying(): Boolean {
        return player.isPlaying
    }

    override fun getBufferPercentage(): Int {
        return 0
    }

    override fun canPause(): Boolean {
        return true
    }

    override fun canSeekBackward(): Boolean {
        return false
    }

    override fun canSeekForward(): Boolean {
        return false
    }

    override fun getAudioSessionId(): Int {
        return player.audioSessionId
    }

    fun setDataSource(path: String?) {
        try {
            player.setDataSource(path)
            state = State.INITIALIZED
            if (needPrepare) {
                player.prepare()
                state = State.PREPARED
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    fun setOnPreparedListener(listener: OnPreparedListener) {
        onPreparedListener = listener
    }

    fun setOnReleaseListener(listener: (() -> Unit)?) {
        onReleaseListener = listener
    }

    private fun initMediaPlayer(){
        player = MediaPlayer()
        player.setOnVideoSizeChangedListener(OnVideoSizeChangedListener { mp, width, height ->
            setFitToFillAspectRatio(mp, width, height)
        })
        player.setOnPreparedListener(OnPreparedListener { mp ->
            state = State.PREPARED
            onPreparedListener?.onPrepared(mp)
        })
    }

    private fun setFitToFillAspectRatio(mp: MediaPlayer?, videoWidth: Int, videoHeight: Int) {
        if (mp == null) return
        val screenWidth: Int
        val screenHeight: Int
        if (BuildConfig.VERSION_CODE >= Build.VERSION_CODES.R) {
            val screenRect = (context as Activity).windowManager.currentWindowMetrics.bounds
            screenWidth = screenRect.width()
            screenHeight = screenRect.height()
        } else {
            screenWidth = context!!.display!!.width
            screenHeight = context.display!!.height
        }

        val videoParams = layoutParams
        if (videoWidth > videoHeight) {
            videoParams.width = screenWidth
            videoParams.height = screenWidth * videoHeight / videoWidth
        } else {
            videoParams.width = screenHeight * videoWidth / videoHeight
            videoParams.height = screenHeight
        }
        layoutParams = videoParams
    }


    private enum class State {
        IDLE, INITIALIZED, PREPARED, STARTED, PAUSED, STOPPED,
    }
}