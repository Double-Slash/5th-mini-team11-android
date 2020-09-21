package doubleslash05.mini.team11.ui.tutorial;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import doubleslash05.mini.team11.R;

public class TutorialPage4 extends Fragment {
    private String title;
    private int page;



    public static doubleslash05.mini.team11.ui.tutorial.TutorialPage1 getInstance(String title, int page) {
        doubleslash05.mini.team11.ui.tutorial.TutorialPage1 tutorialPage1 = new doubleslash05.mini.team11.ui.tutorial.TutorialPage1();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        args.putString("someTitle", title);
        tutorialPage1.setArguments(args);
        return tutorialPage1;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page = getArguments().getInt("someInt", 0);
        title = getArguments().getString("someTitle");


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tutorial_page4, container, false);
        TextView tvLabel = (TextView) view.findViewById(R.id.tvLabel);
        tvLabel.setText("안녕하세요! 이제부터 목소리로 요리해보아요.");
        return view;
    }
}