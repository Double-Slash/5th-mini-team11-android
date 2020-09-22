package doubleslash05.mini.team11.ui.sj;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.LinearLayout;

import java.util.ArrayList;

import doubleslash05.mini.team11.R;
import doubleslash05.mini.team11.ui.base.BaseActivity;

public class MainActivity extends BaseActivity {

    RecyclerView recyclerCategory;
    GridLayoutManager gridLayoutManager;
    CategoryAdapter categoryAdapter;

    RecyclerView recyclerBeginner;
    LinearLayoutManager linearLayoutManager;
    BeginnerAdapter beginnerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        setTitle("COOKICE");

        recyclerCategory = (RecyclerView)findViewById(R.id.recyclerCategory);
        gridLayoutManager = new GridLayoutManager(this,3);

        recyclerCategory.setHasFixedSize(true);
        recyclerCategory.setLayoutManager(gridLayoutManager);

        ArrayList<Category> list = new ArrayList<>();

        list.add(new Category("쌀/콩/견과",R.drawable.category_empty));
        list.add(new Category("쌀/콩/견과",R.drawable.category_empty));
        list.add(new Category("쌀/콩/견과",R.drawable.category_empty));
        list.add(new Category("쌀/콩/견과",R.drawable.category_empty));
        list.add(new Category("쌀/콩/견과",R.drawable.category_empty));
        list.add(new Category("쌀/콩/견과",R.drawable.category_empty));
        list.add(new Category("쌀/콩/견과",R.drawable.category_empty));
        list.add(new Category("쌀/콩/견과",R.drawable.category_empty));
        list.add(new Category("쌀/콩/견과",R.drawable.category_empty));

        categoryAdapter = new CategoryAdapter(this,list);
        recyclerCategory.setAdapter(categoryAdapter);

        recyclerBeginner = (RecyclerView)findViewById(R.id.recyclerBeginner);
        linearLayoutManager = new LinearLayoutManager(this);

        recyclerBeginner.setHasFixedSize(true);
        recyclerBeginner.setLayoutManager(linearLayoutManager);

        ArrayList<Beginner> list2 = new ArrayList<>();

        list2.add(new Beginner("팬케이크","주말에는 여유롭게 브런치",R.drawable.beginner_empty,"15분","초급"));
        list2.add(new Beginner("팬케이크","주말에는 여유롭게 브런치",R.drawable.beginner_empty,"15분","초급"));
        list2.add(new Beginner("팬케이크","주말에는 여유롭게 브런치",R.drawable.beginner_empty,"15분","초급"));

        beginnerAdapter = new BeginnerAdapter(this,list2);
        recyclerBeginner.setAdapter(beginnerAdapter);
    }
}