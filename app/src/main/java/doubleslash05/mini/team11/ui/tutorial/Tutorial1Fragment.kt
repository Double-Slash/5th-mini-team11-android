package doubleslash05.mini.team11.ui.tutorial

import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import doubleslash05.mini.team11.R
import doubleslash05.mini.team11.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_tutorial_page.*

class Tutorial1Fragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        activity
        val view = inflater.inflate(R.layout.fragment_tutorial_page, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        tvGuide.text = "이전"
        tvLabel.setText(Html.fromHtml("<br>안녕하세요!</br><br>이제부터<font color='#FF9E00'>쿠키스</font>와 함께</br><br><font color='#FF9E00'>목소리</font>로 요리해보아요.</br>"))
        tvDetail.setText(Html.fromHtml("레시피 영상 재생시 <font color='#FF9E00'>이전</font> 이라고 말하면<br><font color='#FF9E00'>이전</font> 스텝이 시작됩니다.</br>"))


    }





}