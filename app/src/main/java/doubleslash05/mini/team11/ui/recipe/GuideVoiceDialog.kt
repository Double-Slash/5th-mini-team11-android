package doubleslash05.mini.team11.ui.recipe

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.DialogFragment
import doubleslash05.mini.team11.App
import doubleslash05.mini.team11.R
import doubleslash05.mini.team11.util.extension.getScreenSize
import kotlinx.android.synthetic.main.dialog_guide_voice.view.*

class GuideVoiceDialog : DialogFragment() {
    private val spanColor by lazy { ResourcesCompat.getColor(resources, R.color.mango, null) }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.dialog_guide_voice, container)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.textview_guide_prev.text = getInstructionString(R.string.all_prev, R.string.guide_prev)
        view.textview_guide_next.text = getInstructionString(R.string.all_next, R.string.guide_next)
        view.textview_guide_replay.text = getInstructionString(R.string.all_replay, R.string.guide_replay)

        view.button_guide_close.setOnClickListener {
            dismiss()
        }

        view.checkbox_guide_dont_watch.setOnCheckedChangeListener { _, _ ->
            App.prefs.isShowVoiceGuide = false
            dismiss()
        }

    }


    override fun onResume() {
        super.onResume()

        val screenSize =context!!.getScreenSize()
        val params: ViewGroup.LayoutParams? = dialog?.window?.attributes
        val deviceWidth = screenSize.first
        params?.width = (deviceWidth * 0.9).toInt()
        dialog?.window?.attributes = params as WindowManager.LayoutParams
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    private fun getInstructionString(keyword: Int, description: Int) :SpannableString {
        return getInstructionString(getString(keyword), getString(description))
    }

    private fun getInstructionString(keyword: String, description: String): SpannableString {
        return SpannableString("$keyword $description").apply {
            setSpan(ForegroundColorSpan(spanColor), 0, keyword.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
    }

}