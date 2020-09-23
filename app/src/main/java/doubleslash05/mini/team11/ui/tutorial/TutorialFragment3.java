package doubleslash05.mini.team11.ui.tutorial;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import doubleslash05.mini.team11.R;

public class TutorialFragment3 extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tutorial_page3, container, false);
        TextView tvMain = (TextView) view.findViewById(R.id.tvLabel);
        TextView tvDetail = (TextView) view.findViewById(R.id.tvDetail);


        tvMain.setText(R.string.tutorialPage3Main);
        tvDetail.setText(R.string.tutorialPage3Detail);
        return view;
    }
}