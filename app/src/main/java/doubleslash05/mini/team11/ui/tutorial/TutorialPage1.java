package doubleslash05.mini.team11.ui.tutorial;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import doubleslash05.mini.team11.R;


public class TutorialPage1 extends Fragment {
    private String title;
    private int page;



 public static TutorialPage1 getInstance(String title, int page) {
     TutorialPage1 tutorialPage1 = new TutorialPage1();
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
        View view = inflater.inflate(R.layout.fragment_tutorial_page1, container, false);
        TextView tvLabel = (TextView) view.findViewById(R.id.tvLabel);
        TextView tvDetail = (TextView) view.findViewById(R.id.tvDetail);

        tvLabel.setText("안녕하세요!\n" + "이제부터 목소리로\n"+"요리해보아요.");
        tvDetail.setText("앞으로 등장할 하단의 안내문구를\n" +
                "따라 말해주세요! 5초 이상 인식이 안될 경우\n" +
                "해당 스텝을 스킵할 수 있습니다.\n");

        return view;
    }
}