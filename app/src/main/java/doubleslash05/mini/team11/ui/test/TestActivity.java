package doubleslash05.mini.team11.ui.test;

import android.os.Bundle;

import androidx.annotation.Nullable;

import doubleslash05.mini.team11.R;
import doubleslash05.mini.team11.model.data.RecipeVideoData;
import doubleslash05.mini.team11.ui.base.BaseActivity;
import doubleslash05.mini.team11.ui.common.widget.recipevideo.RecipeVideoView;

public class TestActivity extends BaseActivity {
    RecipeVideoView videoView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        videoView = findViewById(R.id.recipevideo);

    }

    @Override
    protected void onResume() {
        super.onResume();
        RecipeVideoData data = new RecipeVideoData("https://doubleslash-test.s3.ap-northeast-2.amazonaws.com/out.mp4", new Integer[]{50000, 100000});

        videoView.setData(data);
    }
}
