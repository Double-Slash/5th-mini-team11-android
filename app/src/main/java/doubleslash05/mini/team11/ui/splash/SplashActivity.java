package doubleslash05.mini.team11.ui.splash;

<<<<<<< HEAD
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
=======
import android.content.Intent;
>>>>>>> e4a6160d05bef269b6e34a2fbd7226211b765620
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.icaksama.rapidsphinx.RapidPreparationListener;
import com.icaksama.rapidsphinx.RapidSphinx;
import com.icaksama.rapidsphinx.RapidSphinxListener;

import java.util.List;

<<<<<<< HEAD
import doubleslash05.mini.team11.R;
import doubleslash05.mini.team11.ui.tutorial.TutorialActivity;
import edu.cmu.pocketsphinx.Config;
=======
import doubleslash05.mini.team11.ui.main.HomeActivity;
>>>>>>> e4a6160d05bef269b6e34a2fbd7226211b765620

public class SplashActivity extends AppCompatActivity {
    

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
<<<<<<< HEAD

        super.onCreate(savedInstanceState);

=======
        super.onCreate(savedInstanceState);
>>>>>>> e4a6160d05bef269b6e34a2fbd7226211b765620

        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
<<<<<<< HEAD
                Intent intent = new Intent(getApplication(), TutorialActivity.class);
=======
                Intent intent = new Intent(getApplication(), HomeActivity.class);
>>>>>>> e4a6160d05bef269b6e34a2fbd7226211b765620
                startActivity(intent);
                finish();
            }
        }, 1000);
    }


}
