package doubleslash05.mini.team11;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
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


    //    private Button btnStartAudio; 오디오 출력 필요없음.
    private Button btnSync;
    private EditText txtWords;
    private EditText txtDistractor;
    private TextView txtResult;
    private TextView txtStatus;
    private TextView txtPartialResult;
    private TextView txtUnsupported;
    private Button btnStop;

    private ProgressDialog dialog = null;


    // 이것의 용도는 대체 무엇일까요...
    private List<String> finalHyp = new ArrayList<String>();


    // 에러 확인 메소드
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


    // 결과 처리
    @Override
    public void rapidSphinxFinalResult(String result, List<String> hypArr, List<Double> scores) {


        // 소문자 대문자는 영향을 미치지 않게 함.
        if (result.equalsIgnoreCase(txtWords.getText().toString())) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

                txtResult.setTextColor(getResources().getColor(android.R.color.holo_green_light, null));

            } else {
                txtResult.setTextColor(getResources().getColor(android.R.color.holo_green_light));

            }


        } else {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                txtResult.setTextColor(getResources().getColor(android.R.color.holo_red_light, null));

            } else {

                txtResult.setTextColor(getResources().getColor(android.R.color.holo_red_light));

            }

            txtResult.setText(result);
        }

    }

    @Override
    public void rapidSphinxPartialResult(String partialResult) {

        // 부분 음성 처리 메소드 쉽게 말해 한음절 한음절 약간 실시간(?)으로 인식되는 메소드?
        txtPartialResult.setText(partialResult);
    }

    @Override
    public void rapidSphinxUnsupportedWords(List<String> words) {

        // 지원되지 않는 언어에 대한 메소드
        String unsupportedWords = "";
        for (String word : words) {
            unsupportedWords += word + ", ";
        }

        // 지웑되지 않는 언어에 대한 로그 처리 한국어는 되는지 테스트 후 확인해봐야겠음..  -> 확인결과 한국어는 지원이 안됨.
        // -> 하지만 updateVocabulary 메소드에 감지하고 싶은 텍스트를 때려 넣으면 가능 할 것 같음.
        txtUnsupported.setText("지원되지 않는 언어 : \n" + unsupportedWords);
    }

    @Override
    public void rapidSphinxBuffer(short[] shortBuffer, byte[] byteBuffer, boolean inSpeech) {

        System.out.println(shortBuffer.length + " - " + byteBuffer.length + " - " + inSpeech);
        // 아직 뭔지 잘 모르겠음

    }

    // 음성이 감지 되었을때 호출되는 메소드
    @Override
    public void rapidSphinxDidSpeechDetected() {

        txtStatus.setText("단어 감지됨");

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        dialog = ProgressDialog.show(MainActivity.this, "",
                "데이터 준비중..", true);


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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        rapidSphinx = new RapidSphinx(this);
        rapidSphinx.addListener(this);


        //  UI 초기화
        txtWords = findViewById(R.id.txtWords);


        // txtDistractor 가 뭔지 모르겠다.
        txtDistractor = findViewById(R.id.txtDistractor);
        txtResult = findViewById(R.id.txtResult);
        txtPartialResult = findViewById(R.id.txtPartialResult);
        txtUnsupported = findViewById(R.id.txtUnsuported);
        txtStatus = findViewById(R.id.txtStatus);
        btnSync = findViewById(R.id.btnSync);
        btnRecognizer = findViewById(R.id.btnRecognizer);
        btnStop = findViewById(R.id.btnStop);

//        btnStartAudio = (Button) findViewById(R.id.btnStartAudio);
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
                btnRecognizer.setEnabled(true);

                String[] oovwords = {"happy", "dog", "cat"};

                rapidSphinx.updateVocabulary("next",
                        oovwords, new RapidCompletionListener() {
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


        // 인식 시작 버튼 --> 버튼을 안누르고 어떻게 해봐야 할 것 같음. 레시피 클릭시 싱크 버튼을 눌렀다고 치면, 그 후에 자동으로 btnRecognizer 실행 하게끔.
        btnRecognizer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtResult.setText("");
                txtPartialResult.setText("");
                txtStatus.setText("");
                txtUnsupported.setText("");
                btnSync.setEnabled(false);
                btnRecognizer.setEnabled(true);
                rapidSphinx.startRapidSphinx(10000);
                txtStatus.setText("지금 말하세요!");
            }
        });


        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rapidSphinx.stop();
                txtStatus.setText("인식 멈");
            }
        });



        // 권환 확인
        if (isPermissionsGranted()) {


            dialog = ProgressDialog.show(MainActivity.this, "",
                    "데이터 준비중. 기다려주세요...", true);

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
                    btnRecognizer.setEnabled(true);
                    txtStatus.setText("RapidSphinx 준비됨!");
                    dialog.dismiss();
                }
            });
        }

    }


    // 권환 확인 메소드
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
