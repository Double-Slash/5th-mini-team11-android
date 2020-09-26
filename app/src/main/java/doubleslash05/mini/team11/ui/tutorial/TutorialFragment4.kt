package doubleslash05.mini.team11.ui.tutorial

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import doubleslash05.mini.team11.R

class TutorialFragment4 : Fragment() {
    var tvGuide3: TextView? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        activity
        val view = inflater.inflate(R.layout.fragment_tutorial_page4, container, false)
        val tvMain = view.findViewById<View>(R.id.tvLabel) as TextView
        val tvDetail = view.findViewById<View>(R.id.tvDetail) as TextView
        tvGuide3 = view.findViewById<View>(R.id.tvGuide3) as TextView
        tvMain.setText(R.string.tutorialPage4Main)
        tvDetail.setText(R.string.tutorialPage4Detail)
        return view
    }

    fun setTvGuide3(value: String?) {
        tvGuide3!!.text = value
    }
}