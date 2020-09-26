package doubleslash05.mini.team11.ui.splash;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.PersistableBundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.icaksama.rapidsphinx.RapidPreparationListener;
import com.icaksama.rapidsphinx.RapidSphinx;
import com.icaksama.rapidsphinx.RapidSphinxListener;

import java.util.List;

import doubleslash05.mini.team11.R;
import doubleslash05.mini.team11.ui.tutorial.TutorialActivity;
import edu.cmu.pocketsphinx.Config;

public class SplashActivity extends AppCompatActivity {
    

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);


        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplication(), TutorialActivity.class);
                startActivity(intent);
                finish();
            }
        }, 1000);
    }


}
