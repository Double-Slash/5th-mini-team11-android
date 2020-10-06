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
import kotlinx.android.synthetic.main.fragment_tutorial_page1.*

class Tutorial3Fragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        activity
        val view = inflater.inflate(R.layout.fragment_tutorial_page1, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        tvGuide1.text = "다시"
        tvLabel1.setText(R.string.tutorialPage4Main)
        tvDetail1.setText(Html.fromHtml("레시피 영상 재생시 <font color='#FF9E00'>다시</font>라고 말하면 <br>해당스텝이 <font color='#FF9E00'>다시</font> 시작됩니다.</br>"))

    }

}