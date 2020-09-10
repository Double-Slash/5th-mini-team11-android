package doubleslash05.mini.team11;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.icaksama.rapidsphinx.RapidSphinx;
import com.icaksama.rapidsphinx.RapidSphinxListener;

import java.util.List;

public class MainActivity extends AppCompatActivity implements RapidSphinxListener {
    RapidSphinx rapidSphinx;



    @Override
    public void rapidSphinxDidStop(String reason, int code) {

        if (code == 500) {
            // 에러
            System.out.println(reason);

        } else if (code == 522) {

            // 시간 초과 에러

            System.out.println(reason);
        } else if (code == 200) {

            // 정상 처리 스피치 끝났음

            System.out.println(reason);
        }
    }

    @Override
    public void rapidSphinxFinalResult(String result, List<String> hypArr, List<Double> scores) {

    }

    @Override
    public void rapidSphinxPartialResult(String partialResult) {

        // 부분 음성 처리 메소드
        System.out.println(partialResult);
    }

    @Override
    public void rapidSphinxUnsupportedWords(List<String> words) {

        // 지원되지 않는 언어에 대한 메소
        String unsupportedWords = "";
        for (String word: words) {
            unsupportedWords += word + ", ";
        }

        // 지웑되지 않는 언어에 대한 로그 처리
        System.out.println("지원되지 않는 언어 : \n" + unsupportedWords);
    }

    @Override
    public void rapidSphinxDidSpeechDetected() {
        System.out.println("음성 감지됨!");

    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // create obj
        rapidSphinx = new RapidSphinx(this);
        rapidSphinx.addListener(this);
    }
}
