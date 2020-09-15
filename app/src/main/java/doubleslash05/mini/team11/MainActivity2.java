package doubleslash05.mini.team11;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class MainActivity2 extends AppCompatActivity {

    ImageButton im1,im2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("채팅");

        im1 = (ImageButton) findViewById(R.id.im1);
        im2 = (ImageButton) findViewById(R.id.im2);

        RecyclerView recyclerView= findViewById(R.id.recyclerView);

        LinearLayoutManager layoutManager =
                new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        PersonAdapter adapter = new PersonAdapter();

        adapter.addItem(new Person("김민수","aaa","2020.09.14"));
        adapter.addItem(new Person("김하늘","bbb","2020.09.13"));
        adapter.addItem(new Person("홍길동","ccc","2020.09.12"));
        adapter.addItem(new Person("홍길동","ddd","2020.09.11"));
        adapter.addItem(new Person("홍길동","eee","2020.09.10"));
        adapter.addItem(new Person("홍길동","fff","2020.09.9"));
        adapter.addItem(new Person("홍길동","ggg","2020.09.8"));
        adapter.addItem(new Person("홍길동","hhh","2020.09.7"));
        adapter.addItem(new Person("홍길동","iii","2020.09.6"));
        adapter.addItem(new Person("홍길동","jjj","2020.09.5"));

        recyclerView.setAdapter(adapter);

        im1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_01 = new Intent(getApplication(), sub.class);
                startActivity(intent_01);
            }
        });

        im2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_02 = new Intent(getApplication(), MainActivity2.class);
                startActivity(intent_02);
            }
        });
    }
}
