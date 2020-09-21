package doubleslash05.mini.team11.ui.common.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.MediaController;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import doubleslash05.mini.team11.R;

public class RecipeVideoView extends FrameLayout {
    private SurfaceVideoView player;
    private MediaController controller;

    public RecipeVideoView(@NonNull Context context) {
        this(context, null, 0);
    }

    public RecipeVideoView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RecipeVideoView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View v = inflate(context, R.layout.view_recipe_video, this);
        FrameLayout layout = v.findViewById(R.id.layout_recipevideo_video);

        player = new SurfaceVideoView(context);
        controller = new BasicMediaController(context);

        layout.addView(player);
        controller.setAnchorView(layout);
        controller.setMediaPlayer(player);


        v.findViewById(R.id.button_recipevideo).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if(player.isPlaying()){
                    player.pause();
                }else{
                    player.start();
                }
            }
        });

        layout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                controller.show(3000);
            }
        });
    }

}
