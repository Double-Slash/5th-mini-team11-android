package doubleslash05.mini.team11.ui.tutorial

import android.os.Bundle
import android.text.Html
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentTransaction
import doubleslash05.mini.team11.R
import doubleslash05.mini.team11.ui.base.BaseFragment
import kotlinx.android.synthetic.main.activity_tutorial.*
import kotlinx.android.synthetic.main.fragment_tutorial_page.*


class Tutorial3Fragment : BaseFragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        activity
        val view = inflater.inflate(R.layout.fragment_tutorial_page, container, false)
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvDetail.setText(Html.fromHtml("레시피 영상 재생시 <font color= '#FF9E00'>다시</font>라고 말하면<br>해당 스텝이 <font color='#FF9E00'>다시</font> 시작됩니다.</br>"))
        tvGuide.setText("다시")
        tvLabel.setText(Html.fromHtml("이제 다왔어요!<br>마지막으로 따라해주세요!</br>"))

    }

}


