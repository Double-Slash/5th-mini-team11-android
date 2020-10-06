package doubleslash05.mini.team11.ui.sj;

import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

import doubleslash05.mini.team11.R;
import doubleslash05.mini.team11.model.data.Base;
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
        setContentView(R.layout.activity_main2);
        setTitle("COOKICE");
        RecyclerView rv = findViewById(R.id.recyclerCategory);
        CategoryAdapter adapter = new CategoryAdapter();

//        ArrayList<Base> list = new ArrayList<>();
//        list.add(new Text1("카테고리", "주재료를 선택해보세요!"));
//
//        list.add(new Category("쌀/콩/견과", R.drawable.category_empty, null));
//        list.add(new Category("쌀/콩/견과", R.drawable.category_empty, null));
//        list.add(new Category("쌀/콩/견과", R.drawable.category_empty, null));
//
//        list.add(new Text2("달달한것 먹고 기운내요"));
//
//        list.add(new Beginner("팬케이크", R.drawable.beginner_empty, "주말에는 여유롭게 브런치", "15분", "초급",null));
//        list.add(new Beginner("팬케이크", R.drawable.beginner_empty, "주말에는 여유롭게 브런치", "15분", "초급",null));
//        list.add(new Beginner("팬케이크", R.drawable.beginner_empty, "주말에는 여유롭게 브런치", "15분", "초급",null));
//
//        list.add(new Text2("요리 초보를 위한 도전"));

        rv.setAdapter(adapter);
        adapter.setList(getData());

    }

    private ArrayList<Object> getData() {
        ArrayList<Object> list = new ArrayList<>();
        list.add(new Text1("카테고리", "주재료를 선택해보세요!"));
        list.add(new Category(gridData()));
        list.add(new Text2("달달한것 먹고 기운내요"));
        list.add(new Beginner(horizontalData()));
        list.add(new Text2("요리 초보를 위한 도전"));
        list.add(new VerBeginner(verticalData()));
        return list;
    }
    private ArrayList<String> gridData() {
        ArrayList<String> list = new ArrayList<>();
        list.add("쌀/콩/견과");
        list.add("쌀/콩/견과");
        list.add("쌀/콩/견과");
        list.add("쌀/콩/견과");
        list.add("쌀/콩/견과");
        list.add("쌀/콩/견과");
        list.add("쌀/콩/견과");
        list.add("쌀/콩/견과");
        list.add("쌀/콩/견과");

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