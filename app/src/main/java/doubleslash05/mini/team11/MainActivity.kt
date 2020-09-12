package doubleslash05.mini.team11

import android.Manifest
import android.app.ProgressDialog
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.icaksama.rapidsphinx.RapidPreparationListener
import com.icaksama.rapidsphinx.RapidSphinx
import com.icaksama.rapidsphinx.RapidSphinxListener
import edu.cmu.pocketsphinx.Config
import java.util.*

class MainActivity : AppCompatActivity(), RapidSphinxListener {
    private var rapidSphinx: RapidSphinx? = null
    private var btnRecognizer: Button? = null

    //    private Button btnStartAudio; 오디오 출력 필요없음.
    private var btnSync: Button? = null
    private var txtWords: EditText? = null
    private var txtDistractor: EditText? = null
    private var txtResult: TextView? = null
    private var txtStatus: TextView? = null
    private var txtPartialResult: TextView? = null
    private var txtUnsupported: TextView? = null
    private var dialog: ProgressDialog? = null

    // 이것의 용도는 대체 무엇일까요...
    private val finalHyp: List<String> =
        ArrayList()

    // 에러 확인 메소드
    override fun rapidSphinxDidStop(reason: String, code: Int) {
        if (code == 500) {
            // 에러
            println(reason)
        } else if (code == 522) {

            // 시간 초과 에러
            println(reason)
        } else if (code == 200) {

            // 정상 처리 스피치 끝났음
            println(reason)
        }
    }

    // 결과 처리
    override fun rapidSphinxFinalResult(
        result: String,
        hypArr: List<String>,
        scores: List<Double>
    ) {


        // 소문자 대문자는 영향을 미치지 않게 함.
        if (result.equals(txtWords!!.text.toString(), ignoreCase = true)) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                txtResult!!.setTextColor(
                    resources.getColor(
                        android.R.color.holo_green_light,
                        null
                    )
                )
            } else {
                txtResult!!.setTextColor(resources.getColor(android.R.color.holo_green_light, null))
            }
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                txtResult!!.setTextColor(
                    resources.getColor(
                        android.R.color.holo_red_light,
                        null
                    )
                )
            } else {
                txtResult!!.setTextColor(resources.getColor(android.R.color.holo_red_light, null))
            }
            txtResult!!.text = result
        }
    }

    override fun rapidSphinxPartialResult(partialResult: String) {

        // 부분 음성 처리 메소드 쉽게 말해 한음절 한음절 약간 실시간(?)으로 인식되는 메소드?
        txtPartialResult!!.text = partialResult
    }

    override fun rapidSphinxUnsupportedWords(words: List<String>) {

        // 지원되지 않는 언어에 대한 메소드
        var unsupportedWords = ""
        for (word in words) {
            unsupportedWords += "$word, "
        }

        // 지웑되지 않는 언어에 대한 로그 처리 한국어는 되는지 테스트 후 확인해봐야겠음..  -> 확인결과 한국어는 지원이 안됨.
        // -> 하지만 updateVocabulary 메소드에 감지하고 싶은 텍스트를 때려 넣으면 가능 할 것 같음.
        txtUnsupported!!.text = "지원되지 않는 언어 : \n$unsupportedWords"
    }

    override fun rapidSphinxBuffer(
        shortBuffer: ShortArray,
        byteBuffer: ByteArray,
        inSpeech: Boolean
    ) {
        println(shortBuffer.size.toString() + " - " + byteBuffer.size + " - " + inSpeech)
        // 아직 뭔지 잘 모르겠음
    }

    // 음성이 감지 되었을때 호출되는 메소드
    override fun rapidSphinxDidSpeechDetected() {
        txtStatus!!.text = "단어 감지됨"
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        dialog = ProgressDialog.show(
            this@MainActivity, "",
            "데이터 준비중..", true
        )
        rapidSphinx!!.prepareRapidSphinx(object : RapidPreparationListener {
            override fun rapidPreExecute(config: Config) {
                rapidSphinx!!.isRawLogAvailable = true
                config.setString("-logfn", "/dev/null")
                config.setBoolean("-verbose", true)
            }

            override fun rapidPostExecute(isSuccess: Boolean) {
                btnSync!!.isEnabled = true
                btnRecognizer!!.isEnabled = false
                txtStatus!!.text = "RapidSphinx ready!"
                dialog!!.dismiss()
            }
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rapidSphinx = RapidSphinx(this)
        rapidSphinx!!.addListener(this)


        //  UI 초기화
        txtWords = findViewById(R.id.txtWords)


        // txtDistractor 가 뭔지 모르겠다.
        txtDistractor = findViewById(R.id.txtDistractor)
        txtResult = findViewById(R.id.txtResult)
        txtPartialResult = findViewById(R.id.txtPartialResult)
        txtUnsupported = findViewById(R.id.txtUnsuported)
        txtStatus = findViewById(R.id.txtStatus)
        btnSync = findViewById(R.id.btnSync)
        btnRecognizer = findViewById(R.id.btnRecognizer)

//        btnStartAudio = (Button) findViewById(R.id.btnStartAudio);
        txtStatus!!.setText("데이터를 준비하는 중 입니다!")

        // 실행 초기 버튼 비활성화
        btnSync!!.setEnabled(false)
        btnRecognizer!!.isEnabled = false


        // 싱크 버튼을 누를때 어휘를 업데이트한다.
        btnSync!!.setOnClickListener(View.OnClickListener(fun(it: View) {
            dialog!!.show()
            btnSync!!.setEnabled(false)
            btnRecognizer!!.isEnabled = false
            rapidSphinx!!.updateVocabulary(
                "next",
                txtDistractor!!.getText().toString().trim { it <= ' ' }.split(" ".toRegex())
                    .toTypedArray()
            ) {
                txtResult!!.setText("")
                txtPartialResult!!.text = ""
                txtStatus!!.setText("")
                txtUnsupported!!.setText("")
                btnRecognizer!!.isEnabled = true
                dialog!!.dismiss()
            }
        }))



        // 인식 시작 버튼 --> 버튼을 안누르고 어떻게 해봐야 할 것 같음. 레시피 클릭시 싱크 버튼을 눌렀다고 치면, 그 후에 자동으로 btnRecognizer 실행 하게끔.
        btnRecognizer!!.setOnClickListener {
            txtResult!!.text = ""
            txtPartialResult!!.setText("")
            txtStatus!!.setText("")
            txtUnsupported!!.setText("")
            btnSync!!.isEnabled = false
            btnRecognizer!!.setEnabled(false)
            rapidSphinx!!.startRapidSphinx(10000)
            txtStatus!!.setText("지금 말하세요!")
        }


        // 권환 확인
        if (isPermissionsGranted) {
            dialog = ProgressDialog.show(
                this@MainActivity, "",
                "데이터 준비중. 기다려주세요...", true
            )
            rapidSphinx!!.prepareRapidSphinx(object : RapidPreparationListener {
                override fun rapidPreExecute(config: Config) {
                    rapidSphinx!!.isRawLogAvailable = true
                    config.setString("-logfn", "/dev/null")
                    config.setBoolean("-verbose", true)
                }

                override fun rapidPostExecute(isSuccess: Boolean) {
                    btnSync!!.isEnabled = true
                    btnRecognizer!!.isEnabled = false
                    txtStatus!!.text = "RapidSphinx 준비됨!"
                    dialog!!.dismiss()
                }
            })
        }
    }

    // 권환 확인 메소드
    private val isPermissionsGranted: Boolean
        private get() = if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.RECORD_AUDIO
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this, arrayOf(Manifest.permission.RECORD_AUDIO),
                1
            )
            false
        } else {
            true
        }
}