package doubleslash05.mini.team11.ui.common.widget.recipevideo

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.widget.SeekBar
import androidx.appcompat.widget.AppCompatSeekBar
import kotlin.math.abs
import kotlin.math.max

class SectionSeekBar(context: Context, attrs: AttributeSet?, defStyle: Int) : AppCompatSeekBar(context, attrs, defStyle), SeekBar.OnSeekBarChangeListener {

    constructor(context: Context) : this(context, null, 0)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    private var sections: Array<Int>? = null
    private var onSeekBarChangeListener: OnSeekBarChangeListener? = null

    init {
        super.setOnSeekBarChangeListener(this)
    }

    fun setSections(sections: Array<Int>) {
        this.sections = sections
        invalidate()
    }

    override fun setOnSeekBarChangeListener(l: OnSeekBarChangeListener?) {
        this.onSeekBarChangeListener = l
    }

    private var fromuserStick = false
    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {

        if(fromuserStick && !fromUser){
            onSeekBarChangeListener?.onProgressChanged(seekBar, progress, true)
            fromuserStick = false
            return
        }

        val sections = this.sections
        if (fromUser && sections != null) {
            val split = max.toFloat() / SPLIT_LINE_WIDTH_RATE
            for (section in sections) {
                if (abs(progress - section) < STICK_TIME) {
                    fromuserStick = true
                    this.progress = section
                    return
                }
            }
        }
        onSeekBarChangeListener?.onProgressChanged(seekBar, progress, fromUser)
    }

    override fun onStartTrackingTouch(seekBar: SeekBar?) {
        onSeekBarChangeListener?.onStartTrackingTouch(seekBar)
    }

    override fun onStopTrackingTouch(seekBar: SeekBar?) {
        onSeekBarChangeListener?.onStopTrackingTouch(seekBar)
    }

    override fun onDraw(canvas: Canvas) {
        val sections = this.sections
        if (sections == null || sections.size == 1) {
            super.onDraw(canvas)
            return
        }

        val backgroundPaint = Paint().apply {
            color = Color.GRAY
        }
        val fillPaint = Paint().apply {
            color = Color.BLUE
        }

        val margin = max.toFloat() / SPLIT_LINE_WIDTH_RATE
        for (i in 0..sections.size) {
            val prevSection = if (i == 0) 0f else sections[i - 1].toFloat() + margin
            val posSection = if (i == sections.size) max.toFloat() else sections[i].toFloat() - margin

            val startPoint = prevSection / max * width
            val endPoint = posSection / max * width

            if (posSection < progress) {
                canvas.drawRect(startPoint, 0f, endPoint, height.toFloat(), fillPaint)
            } else {
                canvas.drawRect(startPoint, 0f, endPoint, height.toFloat(), backgroundPaint)
                canvas.drawRect(startPoint, 0f, max(0f, progress - prevSection) / max * width + startPoint, height.toFloat(), fillPaint)
            }
        }

    }

    companion object {
        private const val SPLIT_LINE_WIDTH_RATE = 500
        private const val STICK_TIME = 1700
    }
}