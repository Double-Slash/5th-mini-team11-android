package doubleslash05.mini.team11.ui.test;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import doubleslash05.mini.team11.R;
import doubleslash05.mini.team11.model.data.RecipeVideoData;
import doubleslash05.mini.team11.ui.base.BaseActivity;
import doubleslash05.mini.team11.ui.common.widget.recipevideo.RecipeVideoView;

public class TestActivity extends BaseActivity {
    RecipeVideoView videoView;
    RecyclerView recyclerView;
    TestAdapter testAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        videoView = findViewById(R.id.recipevideo);
        recyclerView = findViewById(R.id.recyclerview_main);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        testAdapter = new TestAdapter(this);
        recyclerView.setAdapter(testAdapter);

        @SuppressLint("ResourceType") TestData[] list = new TestData[]{
                new TestData1("Test0", "버튼 0"),
                new TestData2("Test2 - 0", R.drawable.ic_android),
                new TestData1("Test1", "버튼 1"),
                new TestData1("Test2", "버튼 2"),
                new TestData2("Test2 - 1", R.drawable.ic_android),
                new TestData1("Test3", "버튼 3"),
                new TestData1("Test4", "버튼 4"),
                new TestData2("Test2 - 2", R.drawable.ic_android)
        };

        testAdapter.setData(list);
    }

    @Override
    protected void onResume() {
        super.onResume();
        RecipeVideoData data = new RecipeVideoData("https://doubleslash-test.s3.ap-northeast-2.amazonaws.com/out.mp4", new Integer[]{50000, 100000});

        videoView.setData(data);
    }
}
