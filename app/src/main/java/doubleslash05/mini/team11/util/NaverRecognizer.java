package doubleslash05.mini.team11.util;

import android.os.Handler;

import com.naver.speech.clientapi.SpeechConfig;
import com.naver.speech.clientapi.SpeechRecognitionListener;
import com.naver.speech.clientapi.SpeechRecognitionResult;
import com.naver.speech.clientapi.SpeechRecognizer;

public class NaverRecognizer extends SpeechRecognitionListener {


    private Handler mHandler;
    private SpeechRecognizer mRecognizer;

    @Override
    public void onInactive() {

    }

    @Override
    public void onReady() {

    }

    @Override
    public void onRecord(short[] speech) {

    }

    @Override
    public void onPartialResult(String partialResult) {

    }

    @Override
    public void onEndPointDetected() {

    }

    @Override
    public void onResult(SpeechRecognitionResult finalResult) {

    }

    @Override
    public void onError(int errorCode) {

    }

    @Override
    public void onEndPointDetectTypeSelected(SpeechConfig.EndPointDetectType epdType) {

    }
}
