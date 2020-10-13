package doubleslash05.mini.team11.ui.sj;

import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;

import doubleslash05.mini.team11.R;
import doubleslash05.mini.team11.model.data.Beginner;
import doubleslash05.mini.team11.model.data.Category;
import doubleslash05.mini.team11.model.data.Text1;
import doubleslash05.mini.team11.model.data.Text2;
import doubleslash05.mini.team11.model.data.VerBeginner;
import doubleslash05.mini.team11.ui.base.BaseActivity;

public class MainActivity extends BaseActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ui_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);


        RecyclerView rv = findViewById(R.id.recyclerCategory);
        //rv.smoothScrollBy(, 0);
        CategoryAdapter adapter = new CategoryAdapter();
        rv.setAdapter(adapter);
        adapter.setList(getData());

    }




    private ArrayList<Object> getData() {
        ArrayList<Object> list = new ArrayList<>();


        list.add(new Text1("카테고리", "주재료를 선택해보세요!"));

        list.add(new Category(gridData(),gridDataImage()));

        list.add(new Text2("달달한것 먹고 기운내요"));
        list.add(new Beginner(horizontalData()));
        list.add(new Text2("요리 초보를 위한 도전"));
        list.add(new VerBeginner(verticalData()));
        return list;
    }

    private ArrayList<String> gridData() {
        ArrayList<String> list = new ArrayList<>();
        list.add("쌀/콩/견과");
        list.add("밀가루");
        list.add("육류");
        list.add("해산물");
        list.add("채소");
        list.add("과일");
        list.add("달걀");
        list.add("유제품");
        list.add("가공식품");
        return list;
    }
    private ArrayList<Integer> gridDataImage() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(R.drawable.category_empty);
        list.add(R.drawable.category_empty);
        list.add(R.drawable.category_empty);
        list.add(R.drawable.category_empty);
        list.add(R.drawable.category_empty);
        list.add(R.drawable.category_empty);
        list.add(R.drawable.category_empty);
        list.add(R.drawable.category_empty);
        list.add(R.drawable.category_empty);

        return list;
    }



    private ArrayList<String> horizontalData() {
        ArrayList<String> list = new ArrayList<>();
        list.add("aaa");
        list.add("bbb");
        list.add("ccc");

        return list;
    }

    private ArrayList<String> verticalData() {
        ArrayList<String> list = new ArrayList<>();
        list.add("aaa");
        list.add("bbb");
        list.add("ccc");

        return list;
    }


}