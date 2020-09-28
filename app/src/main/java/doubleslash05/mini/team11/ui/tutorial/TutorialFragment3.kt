package doubleslash05.mini.team11.ui.tutorial

import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import doubleslash05.mini.team11.R

class TutorialFragment3 : Fragment() {
    var tvGuide3: TextView? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        activity
        val view = inflater.inflate(R.layout.fragment_tutorial_page3, container, false)
        val tvMain = view.findViewById<View>(R.id.tvLabel) as TextView
        val tvDetail = view.findViewById<View>(R.id.tvDetail) as TextView
        tvGuide3 = view.findViewById<View>(R.id.tvGuide3) as TextView
        tvMain.setText(R.string.tutorialPage4Main)
        tvDetail.setText(Html.fromHtml("레시피 영상 재생시 <font color='#FF9E00'>다시</font>라고 말하면 <br>해당스텝이 <font color='#FF9E00'>다시</font> 시작됩니다.</br>"))
        return view
    }

}