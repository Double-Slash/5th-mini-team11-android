package doubleslash05.mini.team11.ui.sj;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

import doubleslash05.mini.team11.R;
import doubleslash05.mini.team11.model.data.Button;
import doubleslash05.mini.team11.model.data.Recipe;
import doubleslash05.mini.team11.model.data.Recipe_Base;
import doubleslash05.mini.team11.model.data.Step;
import doubleslash05.mini.team11.model.data.Text3;

public class RecipeActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecipeAdapter recipeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        setTitle("팬케이크");


        recyclerView = findViewById(R.id.recyclerRecipe);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recipeAdapter = new RecipeAdapter(this);
        recyclerView.setAdapter(recipeAdapter);

        TabLayout tabs=(TabLayout) findViewById(R.id.tabs);
        /*tabs.addTab(tabs.newTab().setText("재료 & 정보"));
        tabs.addTab(tabs.newTab().setText("레시피"));
        tabs.setTabGravity(tabs.GRAVITY_CENTER);


        final ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        final PageAdapter myPageAdapter = new PageAdapter(getSupportFragmentManager(),2);
        viewPager.setAdapter(myPageAdapter);

        tabs.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabs));*/

        Recipe_Base[] list = new Recipe_Base[]{
                new Recipe("주말에는 여유롭게 브런치", "팬케이크", "15분", "초급"),



                new Step("STEP 1", R.drawable.step_empty),
                new Text3("1. 믹스와 물을 잘 섞어줍니다."),
                new Text3("2. 반죽에 계란을 넣고 섞습니다."),
                new Text3("3. 멍울이 지지 않게 풀어줍니다."),

                new Step("STEP 2", R.drawable.step_empty),
                new Text3("1. 달궈진 팬에 식용유를 두르고 약불로."),
                new Text3("2. 반죽을 국자의 반정도 퍼서 동그랗게"),
                new Text3("3. 반죽 표면에 기포가 보글보글 올라오면 뒤집개를 이용해 뒤집어줍니다"),

                new Step("STEP 3", R.drawable.step_empty),
                new Text3("1. 뒤집은 상태에서 약 1분간 기다린후 불을 꺼줍니다."),
                new Text3("2. 시럽을 붓고 버터를 올립니다."),
                new Text3("3. 기호에 따라 과일 등 다양한 토핑을 곁들입니다."),

                new Button(),
        };
        recipeAdapter.setData(list);
    }
}