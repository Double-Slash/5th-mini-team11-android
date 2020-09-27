package doubleslash05.mini.team11.ui.tutorial

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import doubleslash05.mini.team11.R

class TutorialFragment2 : Fragment() {
    var tvGuide1: TextView? = null
    var tvResult: TextView? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        activity
        val view = inflater.inflate(R.layout.fragment_tutorial_page2, container, false)
        val tvMain = view.findViewById<View>(R.id.tvLabel) as TextView
        val tvDetail = view.findViewById<View>(R.id.tvDetail) as TextView
        tvGuide1 = view.findViewById<View>(R.id.tvGuide1) as TextView
        tvMain.setText(R.string.tutorialPage1Main)
        tvDetail.setText(R.string.tutorialPage1Detail)
        tvGuide1!!.text = "stop"
        return view
    }

    fun setTvGuide1(value: String?) {
        tvGuide1!!.text = value
    }
}