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

}