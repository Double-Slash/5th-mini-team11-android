package doubleslash05.mini.team11.ui.sj;

import android.app.ActionBar;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;

import doubleslash05.mini.team11.R;
import doubleslash05.mini.team11.model.data.Beginner;
import doubleslash05.mini.team11.model.data.Category;
import doubleslash05.mini.team11.model.data.Fragment1;
import doubleslash05.mini.team11.model.data.MainViewPager;
import doubleslash05.mini.team11.model.data.Text1;
import doubleslash05.mini.team11.model.data.Text2;
import doubleslash05.mini.team11.model.data.VerBeginner;
import doubleslash05.mini.team11.ui.base.BaseActivity;
import me.relex.circleindicator.CircleIndicator3;

public class MainActivity extends BaseActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        RecyclerView rv = findViewById(R.id.recyclerCategory);
        CategoryAdapter adapter = new CategoryAdapter();
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