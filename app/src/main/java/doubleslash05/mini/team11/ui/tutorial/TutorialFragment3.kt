package doubleslash05.mini.team11.ui.tutorial

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import doubleslash05.mini.team11.R

class TutorialFragment3 : Fragment() {
    var tvGuide2: TextView? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        activity
        val view = inflater.inflate(R.layout.fragment_tutorial_page3, container, false)
        val tvMain = view.findViewById<View>(R.id.tvLabel) as TextView
        val tvDetail = view.findViewById<View>(R.id.tvDetail) as TextView
        tvGuide2 = view.findViewById<View>(R.id.tvGuide2) as TextView
        tvMain.setText(R.string.tutorialPage3Main)
        tvDetail.setText(R.string.tutorialPage3Detail)
        return view
    }

    fun setTvGuide2(value: String?) {
        tvGuide2!!.text = value
    }
}