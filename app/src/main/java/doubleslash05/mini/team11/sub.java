package doubleslash05.mini.team11;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class sub extends AppCompatActivity {

    private ImageButton im3,im4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        setTitle("친구");
        im3 = (ImageButton) findViewById(R.id.im3);
        im4=(ImageButton) findViewById(R.id.im4);

        RecyclerView recyclerView2 = findViewById(R.id.recyclerView2);

        LinearLayoutManager layoutManager2 =
                new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);

        recyclerView2.setLayoutManager(layoutManager2);

        FriendAdapter adapter2 = new FriendAdapter();

        adapter2.addItem(new Friend("김영수","좋음",R.drawable.person,"발라드"));
        adapter2.addItem(new Friend("박민석","슬픔",R.drawable.person1,"힙합"));
        adapter2.addItem(new Friend("홍길동","나쁨",R.drawable.person2,"잔잔한노래"));
        adapter2.addItem(new Friend("김민석","슬픔",R.drawable.person1,"힙합"));
        adapter2.addItem(new Friend("백민석","슬픔",R.drawable.person,"발라드"));
        adapter2.addItem(new Friend("김민석","슬픔",R.drawable.person1,"힙합"));
        adapter2.addItem(new Friend("홍길동","좋음",R.drawable.person2,"발라드"));
        adapter2.addItem(new Friend("홍길동","나쁨",R.drawable.person,"발라드"));
        adapter2.addItem(new Friend("홍길동","좋음",R.drawable.person1,"7080"));





        recyclerView2.setAdapter(adapter2);

        im3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_01 = new Intent(getApplication(), sub.class);
                startActivity(intent_01);
            }
        });

        im4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_02 = new Intent(getApplication(), MainActivity2.class);
                startActivity(intent_02);
            }
        });
    }
}
