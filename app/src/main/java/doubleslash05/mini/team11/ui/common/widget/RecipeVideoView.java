package doubleslash05.mini.team11.ui.common.widget;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.MediaController;
import android.widget.SeekBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import doubleslash05.mini.team11.R;
import doubleslash05.mini.team11.util.Log;

public class RecipeVideoView extends FrameLayout implements SeekBar.OnSeekBarChangeListener, MediaPlayer.OnPreparedListener {
    private SurfaceVideoView player;
    private MediaController controller;

    private Thread seekThread = new Thread(new Runnable() {
        @Override
        public void run() {
            while (true) {
                if (player.isPlaying()) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            seekBar.setProgress(player.getCurrentPosition());
                        }
                    });
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    });;
    private SeekBar seekBar;
    private Button playButton;

    final Handler handler = new Handler(Looper.getMainLooper());

    public RecipeVideoView(@NonNull Context context) {
        this(context, null, 0);
    }

    public RecipeVideoView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RecipeVideoView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        player = new SurfaceVideoView(context);
        controller = new BasicMediaController(context);


        View v = inflate(context, R.layout.view_recipe_video, this);
        // FindViewById
        FrameLayout layout = v.findViewById(R.id.layout_recipevideo_video);
        seekBar = v.findViewById(R.id.seekbar_recipevideo);
        playButton = v.findViewById(R.id.button_recipevideo);


        controller.setAnchorView(layout);
        controller.setMediaPlayer(player);

        seekBar.setEnabled(false);
        playButton.setEnabled(false);


        // Player
        player.setOnPreparedListener(this);
        player.setDataSource();
        layout.addView(player);

        seekBar.setOnSeekBarChangeListener(this);

        playButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (player.isPlaying()) {
                    player.pause();
                } else {
                    player.start();
                }
            }
        });

        layout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
//                controller.show(3000);
            }
        });
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if (!fromUser) return;
        Log.d("RecipeVideo", progress + "");
        player.seekTo(progress);
    }

    private boolean trackingStart = false;

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        trackingStart = player.isPlaying();
        player.pause();
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        if (trackingStart) player.start();
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        seekBar.setMax(mp.getDuration());
        seekBar.setEnabled(true);
        playButton.setEnabled(true);
        seekThread.start();
    }
}
