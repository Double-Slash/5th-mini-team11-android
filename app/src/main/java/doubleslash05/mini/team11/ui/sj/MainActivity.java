package doubleslash05.mini.team11.ui.sj;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

import doubleslash05.mini.team11.R;
import doubleslash05.mini.team11.UiData.Base;
import doubleslash05.mini.team11.UiData.Beginner;
import doubleslash05.mini.team11.UiData.Category;
import doubleslash05.mini.team11.UiData.Text1;
import doubleslash05.mini.team11.UiData.Text2;
import doubleslash05.mini.team11.ui.base.BaseActivity;

import static android.widget.LinearLayout.VERTICAL;

public class MainActivity extends BaseActivity {

     RecyclerView recyclerView;
     CategoryAdapter categoryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        setTitle("COOKICE");



        recyclerView = findViewById(R.id.recyclerCategory);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        categoryAdapter = new CategoryAdapter(this);
        recyclerView.setAdapter(categoryAdapter);


        Base[] list = new Base[]{
          new Text1("카테고리","주재료를 선택해보세요!"),

                new Category("쌀/콩/견과",R.drawable.category_empty),
                new Category("쌀/콩/견과",R.drawable.category_empty),
                new Category("쌀/콩/견과",R.drawable.category_empty),
                new Category("쌀/콩/견과",R.drawable.category_empty),
                new Category("쌀/콩/견과",R.drawable.category_empty),
                new Category("쌀/콩/견과",R.drawable.category_empty),
                new Category("쌀/콩/견과",R.drawable.category_empty),
                new Category("쌀/콩/견과",R.drawable.category_empty),
                new Category("쌀/콩/견과",R.drawable.category_empty),

                new Text2("달달한것 먹고 기운내요"),
                new Text2("요리 초보를 위한 도전"),

                new Beginner("팬케이크",R.drawable.beginner_empty,"주말에는 여유롭게 브런치","15분","초급"),
                new Beginner("팬케이크",R.drawable.beginner_empty,"주말에는 여유롭게 브런치","15분","초급"),
                new Beginner("팬케이크",R.drawable.beginner_empty,"주말에는 여유롭게 브런치","15분","초급"),
        };

        categoryAdapter.setData(list);
    }

}
