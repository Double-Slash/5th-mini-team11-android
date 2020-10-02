package doubleslash05.mini.team11.ui.tutorial

import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import doubleslash05.mini.team11.R
import doubleslash05.mini.team11.ui.base.BaseFragment

class TutorialFragment4 : BaseFragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        activity
        val view = inflater.inflate(R.layout.fragment_tutorial_page4, container, false)
        val tvMain = view.findViewById<View>(R.id.tvLabel) as TextView
        val tvDetail = view.findViewById<View>(R.id.tvDetail) as TextView
        tvMain.setText(Html.fromHtml("수고하셨어요 :)<br>이제부터 요리할 때</br><br><font color='#FF9E00'>스크린</font>에 <font color='#FF9E00'>손</font>은 <font color='#FF9E00'>그만</font>!</br>"))
        tvDetail.setText(R.string.tutorialPage5Detail)
        return view
    }
}