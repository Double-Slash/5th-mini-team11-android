package doubleslash05.mini.team11;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.icaksama.rapidsphinx.RapidCompletionListener;
import com.icaksama.rapidsphinx.RapidPreparationListener;
import com.icaksama.rapidsphinx.RapidSphinx;
import com.icaksama.rapidsphinx.RapidSphinxListener;

import java.util.ArrayList;
import java.util.List;

import edu.cmu.pocketsphinx.Config;

public class MainActivity extends AppCompatActivity implements RapidSphinxListener {

    private RapidSphinx rapidSphinx;
    private Button btnRecognizer;
    private Button btnStartAudio;
    private Button btnSync;
    private EditText txtWords;
    private EditText txtDistractor;
    private TextView txtResult;
    private TextView txtStatus;
    private TextView txtPartialResult;
    private TextView txtUnsupported;

    private ProgressDialog dialog = null;

    private List<String> finalHyp = new ArrayList<String>();









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


        System.out.println("Full Result : " + result);
        // NOTE :
        // [x] parameter "result" : Give final response with ??? values when word out-of-vocabulary.
        // [x] parameter "hypArr" : Give final response in original words without ??? values.

        // Get score from every single word. hypArr length equal with scores length
        for (double score: scores) {
            System.out.println(score);
        }

        // Get array word
        for (String word: hypArr) {
            System.out.println(word);
        }
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
    public void rapidSphinxBuffer(short[] shortBuffer, byte[] byteBuffer, boolean inSpeech) {



        // 아직 뭔지 잘 모르겠음

    }
    @Override
    public void rapidSphinxDidSpeechDetected() {

        System.out.println("단어 감지됨");

    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        rapidSphinx = new RapidSphinx(this);
        rapidSphinx.addListener(this);




        // 초기화
        txtWords = (EditText) findViewById(R.id.txtWords);
        txtDistractor = (EditText) findViewById(R.id.txtDistractor);
        txtResult = (TextView) findViewById(R.id.txtResult);
        txtPartialResult = (TextView) findViewById(R.id.txtPartialResult);
        txtUnsupported = (TextView) findViewById(R.id.txtUnsuported);
        txtStatus = (TextView) findViewById(R.id.txtStatus);
        btnSync = (Button) findViewById(R.id.btnSync);
        btnRecognizer = (Button) findViewById(R.id.btnRecognizer);
        btnStartAudio = (Button) findViewById(R.id.btnStartAudio);
        txtStatus.setText("데이터를 준비하는 중 입니다!");

        // 실행 초기 버튼 비활성화
        btnSync.setEnabled(false);
        btnRecognizer.setEnabled(false);


        // 싱크 버튼을 누를때 어휘를 업데이트한다.
        btnSync.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.show();
                btnSync.setEnabled(false);
                btnRecognizer.setEnabled(false);

                rapidSphinx.updateVocabulary(txtWords.getText().toString().trim(),
                        txtDistractor.getText().toString().trim().split(" "), new RapidCompletionListener() {
                            @Override
                            public void rapidCompletedProcess() {

                                txtResult.setText("");
                                txtPartialResult.setText("");
                                txtStatus.setText("");
                                txtUnsupported.setText("");
                                btnRecognizer.setEnabled(true);
                                dialog.dismiss();
                            }
                        });

            }
        });



        // 권환 확인
        if (isPermissionsGranted()) {


            dialog = ProgressDialog.show(MainActivity.this, "",
                    "Preparing data. Please wait...", true);

            rapidSphinx.prepareRapidSphinx(new RapidPreparationListener() {
                @Override
                public void rapidPreExecute(Config config) {

                    rapidSphinx.setRawLogAvailable(true);
                    config.setString("-logfn", "/dev/null");
                    config.setBoolean("-verbose", true);
                }

                @Override
                public void rapidPostExecute(boolean isSuccess) {

                    btnSync.setEnabled(true);
                    btnRecognizer.setEnabled(false);
                    txtStatus.setText("RapidSphinx ready!");
                    dialog.dismiss();
                }
            });
        }

    }





    private boolean isPermissionsGranted() {

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.RECORD_AUDIO},
                    1);
            return false;
        } else {
            return true;
        }

    }



}
