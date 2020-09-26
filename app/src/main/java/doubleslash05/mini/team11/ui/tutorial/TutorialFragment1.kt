package doubleslash05.mini.team11.ui.tutorial

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.icaksama.rapidsphinx.RapidSphinx
import doubleslash05.mini.team11.R

class TutorialFragment1 : Fragment() {
    private val rapidSphinx: RapidSphinx? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        activity
        val view = inflater.inflate(R.layout.fragment_tutorial_page1, container, false)
        val tvMain = view.findViewById<View>(R.id.tvLabel) as TextView
        val tvDetail = view.findViewById<View>(R.id.tvDetail) as TextView
        tvMain.setText(R.string.tutorialPage1Main)
        tvDetail.setText(R.string.tutorialPage1Detail)
        return view
    }
}