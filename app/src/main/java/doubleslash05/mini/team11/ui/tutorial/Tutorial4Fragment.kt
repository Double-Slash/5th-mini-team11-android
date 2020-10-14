package doubleslash05.mini.team11.ui.tutorial

import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import doubleslash05.mini.team11.R
import doubleslash05.mini.team11.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_tutorial_page.*

class Tutorial4Fragment : BaseFragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        activity
        val view = inflater.inflate(R.layout.fragment_tutorial_page, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        tvDetail.setText(R.string.tutorial_finish)
        tvLabel.setText(Html.fromHtml("수고하셨어요 :)<br>이제부터 요리할 때</br><br><font color='#FF9E00'>스크린</font>에 <font color='#FF9E00'>손</font>은 <font color='#FF9E00'>그만</font>!</br>"))
        imageview_logo.visibility = View.INVISIBLE
        view_line.visibility = View.INVISIBLE
    }
}

