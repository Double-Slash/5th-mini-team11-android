package doubleslash05.mini.team11;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class sub extends AppCompatActivity {

    ImageButton im3,im4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        im3 = (ImageButton) findViewById(R.id.im3);
        im4=(ImageButton) findViewById(R.id.im4);


        setTitle("친구");

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
