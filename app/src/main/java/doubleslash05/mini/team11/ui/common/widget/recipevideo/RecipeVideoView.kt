package doubleslash05.mini.team11.ui.common.widget.recipevideo

import android.annotation.SuppressLint
import android.content.Context
import android.media.MediaPlayer.OnPreparedListener
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.VelocityTracker
import android.view.View
import android.view.View.OnClickListener
import android.widget.FrameLayout
import android.widget.MediaController.MediaPlayerControl
import android.widget.SeekBar
import com.icaksama.rapidsphinx.RapidSphinx
import doubleslash05.mini.team11.R
import doubleslash05.mini.team11.model.data.RecipeVideoData
import doubleslash05.mini.team11.util.LogUtils
import kotlinx.android.synthetic.main.view_recipe_video.view.*
import kotlinx.coroutines.*
import kotlin.math.abs

@SuppressLint("ClickableViewAccessibility")
class RecipeVideoView(context: Context, attrs: AttributeSet?, defStyle: Int) : FrameLayout(context, attrs, defStyle), MediaPlayerControl, SeekBar.OnSeekBarChangeListener {
    private lateinit var data: RecipeVideoData
    private lateinit var seekBarCoroutine: Job
    private var onChangeSectionListener: OnChangeSectionListener? = null
    private var onStartListener: (() -> Unit)? = null

    constructor(context: Context) : this(context, null, 0)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    init {
        val v = View.inflate(context, R.layout.view_recipe_video, this)

        seekbar_recipevideo.isEnabled = false
        button_recipevideo_start.isEnabled = false

        button_recipevideo_next.visibility = View.GONE
        button_recipevideo_prev.visibility = View.GONE
        button_recipevideo_replay.visibility = View.GONE

        // player_recipevideo
        player_recipevideo.setOnPreparedListener(OnPreparedListener { mp ->
            seekbar_recipevideo.max = mp.duration
            seekbar_recipevideo.isEnabled = true
            button_recipevideo_start.isEnabled = true

            seekBarCoroutine = GlobalScope.launch {
                while (true) {
                    delay(TICK_TIME)
                    launch(Dispatchers.Main) {
                        tick()
                    }
                }
            }
        })

        player_recipevideo.setOnReleaseListener {
            seekBarCoroutine.cancel()
        }


        seekbar_recipevideo.setOnSeekBarChangeListener(this)

        val velocityTracker: VelocityTracker = VelocityTracker.obtain()
        player_recipevideo.setOnTouchListener { v, event ->
            when (event!!.action) {
                MotionEvent.ACTION_DOWN -> {
                    velocityTracker.clear()
                    return@setOnTouchListener true
                }
                MotionEvent.ACTION_MOVE -> {
                    velocityTracker.addMovement(event)
                    return@setOnTouchListener true
                }
                MotionEvent.ACTION_UP -> {
                    velocityTracker.computeCurrentVelocity(250)
                    if (velocityTracker.xVelocity > 500) {
                        nextSection()
                    } else if (velocityTracker.xVelocity < -500) {
                        prevSection()
                    } else if (event.eventTime - event.downTime < 200) {
                        switchController()
                    } else {
                        return@setOnTouchListener false
                    }
                    return@setOnTouchListener true
                }
            }
            return@setOnTouchListener false
        }

        button_recipevideo_start.setOnClickListener {
            hideController()

            button_recipevideo_next.visibility = View.VISIBLE
            button_recipevideo_prev.visibility = View.VISIBLE
            button_recipevideo_replay.visibility = View.VISIBLE
            button_recipevideo_start.visibility = View.GONE
            start()

            onStartListener?.invoke()
        }

        button_recipevideo_replay.setOnClickListener {
            replySction()
        }

        button_recipevideo_next.setOnClickListener {
            nextSection()
        }

        button_recipevideo_prev.setOnClickListener {
            prevSection()
        }
    }

    // region seekbar
    private var trackingStart = false
    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
        if (!fromUser) return
        player_recipevideo.seekTo(progress)
        onChangeSectionListener?.onChangeSection(data.getSectionIndex(progress))
    }

    override fun onStartTrackingTouch(seekBar: SeekBar?) {
        trackingStart = player_recipevideo.isPlaying
        player_recipevideo.pause()
    }

    override fun onStopTrackingTouch(seekBar: SeekBar?) {
        if (trackingStart) player_recipevideo.start()
    }

    //endregion

    // region MediaPlayer Controller
    override fun start() {
        hideController()
        player_recipevideo.start()
        if (!isStopEndSection) {
            onChangeSectionListener?.onChangeSection(data.getSectionIndex(currentPosition))
        }
    }

    override fun pause() {
        player_recipevideo.pause()
    }

    fun nextSection() {
        isStopEndSection = false
        seekTo(data.getNextSection(currentPosition))
        start()
    }

    fun replySction() {
        isStopEndSection = false
        seekTo(data.getCurrentSction(currentPosition))
        start()
    }

    fun prevSection() {
        isStopEndSection = false
        seekTo(data.getPrevSection(currentPosition))
        start()
    }

    override fun getDuration(): Int {
        return player_recipevideo.duration
    }

    override fun getCurrentPosition(): Int {
        return player_recipevideo.currentPosition
    }

    override fun seekTo(pos: Int) {
        seekbar_recipevideo.progress = pos
        player_recipevideo.seekTo(pos)
        onChangeSectionListener?.onChangeSection(data.getSectionIndex(pos))
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
    //endregion


    // region Controller
    fun switchController() {
        if (layout_recipevideo_controller.visibility == View.VISIBLE) {
            hideController()
        } else {
            showController()
        }
    }

    fun showController() {
        layout_recipevideo_controller.visibility = View.VISIBLE
    }

    fun hideController() {
        layout_recipevideo_controller.visibility = View.GONE
    }
    //endregion

    fun setData(data: RecipeVideoData) {
        this.data = data
        player_recipevideo.setDataSource(data.path)
        seekbar_recipevideo.setSections(data.sections)
    }

    fun setOnChangeSectionListener(listener: OnChangeSectionListener) {
        this.onChangeSectionListener = listener
    }

    fun setOnStartLisner(listener: (() -> Unit)?){
        onStartListener = listener
    }

    // true 일경우 Section이 끝나면 Stop
    private var isStopEndSection = false

    // 매 틱마다 UI 및 로직 처리
    private fun tick() {
        val currentPosition = player_recipevideo.currentPosition
        seekbar_recipevideo.progress = currentPosition

        var ch = false
        for (section in data.sections) {
            val diff = abs(section - currentPosition)
            if (diff <= TICK_TIME) {
                if (!isStopEndSection) return
                pause()
                showController()
                isStopEndSection = false
                break
            }else if (diff <= TICK_TIME * 3){
                ch = true
            }
        }

        if (!ch) {
            isStopEndSection = true
        }
    }

    interface OnChangeSectionListener {
        fun onChangeSection(index: Int)
    }

    companion object {
        private const val TICK_TIME = 300L
    }
}
