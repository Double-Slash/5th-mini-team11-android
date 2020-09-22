package doubleslash05.mini.team11.ui.common.widget.recipevideo

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatSeekBar

class SectionSeekBar(context: Context, attrs: AttributeSet?, defStyle: Int) : AppCompatSeekBar(context, attrs, defStyle) {

    constructor(context: Context) : this(context, null, 0)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    private var sections: Array<Int>? = null

    fun setSections(sections: Array<Int>) {
        this.sections = sections
        invalidate()
    }

    override fun onDraw(canvas: Canvas) {
        val sections = this.sections
        if (sections == null) {
            super.onDraw(canvas)
            return
        }

        val backgroundPaint = Paint().apply {
            color = Color.GRAY
        }
        val fillPaint = Paint().apply {
            color = Color.BLUE
        }

        for (i in 0 until sections.size) {
            canvas.drawLine(0f, 0f, sections[i].toFloat(), height.toFloat(), backgroundPaint)
        }

    }
}