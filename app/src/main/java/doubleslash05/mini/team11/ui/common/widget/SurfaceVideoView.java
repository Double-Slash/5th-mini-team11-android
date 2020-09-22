package doubleslash05.mini.team11.ui.common.widget;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.media.MediaPlayer;
import android.os.Build;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.ViewGroup;
import android.widget.MediaController;

import androidx.annotation.NonNull;

import java.io.IOException;

import doubleslash05.mini.team11.BuildConfig;
import doubleslash05.mini.team11.util.Log;


/**
 * @author HoYean Lee
 * MediaPlayer에서 화면을 담당하는 Class
 */
class SurfaceVideoView extends SurfaceView implements SurfaceHolder.Callback, MediaController.MediaPlayerControl {
    private MediaPlayer player;
    private Context context;

    private State state = State.IDLE;
    private MediaPlayer.OnPreparedListener onPreparedListener = null;

    public SurfaceVideoView(Context context) {
        super(context);

        this.context = context;

        player = new MediaPlayer();
        player.setOnVideoSizeChangedListener(new MediaPlayer.OnVideoSizeChangedListener() {

            @Override
            public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {
                setFitToFillAspectRatio(mp, width, height);
            }
        });
        player.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                state = State.PREPARED;
                if(onPreparedListener != null) onPreparedListener.onPrepared(mp);
            }
        });

        getHolder().addCallback(this);
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
        player.setDisplay(surfaceHolder);
        try {
            player.prepare();
            state = State.PREPARED;
        } catch (IOException e) {
            Log.e("RecipeVideo", "URL Error", e);
        }
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {
//        boolean isValidState = (mTargetState == STATE_PLAYING);
//        boolean hasValidSize = (mVideoWidth == w && mVideoHeight == h);
//        if (mMediaPlayer != null && isValidState && hasValidSize) {
//            if (mSeekWhenPrepared != 0) {
//                seekTo(mSeekWhenPrepared);
//            }
//            start();
//        }
    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {
        player.release();
    }

    private void setFitToFillAspectRatio(MediaPlayer mp, int videoWidth, int videoHeight) {
        if (mp == null) return;

        int screenWidth;
        int screenHeight;


        if (BuildConfig.VERSION_CODE >= Build.VERSION_CODES.R) {
            Rect screenRect = ((Activity) context).getWindowManager().getCurrentWindowMetrics().getBounds();
            screenWidth = screenRect.width();
            screenHeight = screenRect.height();

        } else {
            screenWidth = context.getDisplay().getWidth();
            screenHeight = context.getDisplay().getHeight();
            ;
        }


        ViewGroup.LayoutParams videoParams = getLayoutParams();
        if (videoWidth > videoHeight) {
            videoParams.width = screenWidth;
            videoParams.height = screenWidth * videoHeight / videoWidth;
        } else {
            videoParams.width = screenHeight * videoWidth / videoHeight;
            videoParams.height = screenHeight;
        }

        setLayoutParams(videoParams);
    }

    @Override
    public void start() {
        switch (state) {
            case PREPARED:
            case STARTED:
            case PAUSED:
                player.start();
                state = State.STARTED;
            default:
                break;
        }
    }

    @Override
    public void pause() {
        switch (state) {
            case PREPARED:
            case STARTED:
            case PAUSED:
                player.pause();
                state = State.PAUSED;
            default:
                break;
        }
    }

    @Override
    public int getDuration() {
        switch (state) {
            case PREPARED:
            case STARTED:
            case PAUSED:
            case STOPPED:
            return player.getDuration();
        }
        return 0;
    }

    @Override
    public int getCurrentPosition() {
        return player.getCurrentPosition();
    }

    @Override
    public void seekTo(int pos) {
        switch (state) {
            case PREPARED:
            case STARTED:
            case PAUSED:
                player.seekTo(pos);
                break;
            default:
                break;
        }
    }

    @Override
    public boolean isPlaying() {
        return player.isPlaying();
    }

    @Override
    public int getBufferPercentage() {
        return 0;
    }

    @Override
    public boolean canPause() {
        return true;
    }

    @Override
    public boolean canSeekBackward() {
        return false;
    }

    @Override
    public boolean canSeekForward() {
        return false;
    }

    @Override
    public int getAudioSessionId() {
        return player.getAudioSessionId();
    }

    public void setDataSource() {
        try {
            player.setDataSource("https://doubleslash-test.s3.ap-northeast-2.amazonaws.com/out.mp4");
            state = State.INITIALIZED;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setOnPreparedListener(MediaPlayer.OnPreparedListener listener) {
        onPreparedListener = listener;
    }

    private enum State {
        IDLE,
        INITIALIZED,
        PREPARED,
        STARTED,
        PAUSED,
        STOPPED,
    }
}
