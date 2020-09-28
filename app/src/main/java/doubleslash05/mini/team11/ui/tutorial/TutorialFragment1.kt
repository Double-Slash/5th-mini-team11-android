package doubleslash05.mini.team11.ui.tutorial

import android.graphics.Color
import android.os.Bundle
import android.text.Html
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import doubleslash05.mini.team11.R

class TutorialFragment1 : Fragment() {
    var tvGuide1: TextView? = null
    var tvResult: TextView? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        activity
        val view = inflater.inflate(R.layout.fragment_tutorial_page1, container, false)
        val tvMain = view.findViewById<View>(R.id.tvLabel) as TextView
        val tvDetail = view.findViewById<View>(R.id.tvDetail) as TextView


        tvGuide1 = view.findViewById<View>(R.id.tvGuide1) as TextView
        tvMain.setText(Html.fromHtml("<br>안녕하세요!</br><br>이제부터<font color='#FF9E00'>쿠키스</font>와 함께</br><br><font color='#FF9E00'>목소리</font>로 요리해보아요.</br>"))
        tvDetail.setText(Html.fromHtml("레시피 영상 재생시 <font color='#FF9E00'>이전</font> 이라고 말하면<br><font color='#FF9E00'>이전</font> 스텝이 시작됩니다.</br>"))
        tvGuide1!!.text = "stop"
        return view
    }

}