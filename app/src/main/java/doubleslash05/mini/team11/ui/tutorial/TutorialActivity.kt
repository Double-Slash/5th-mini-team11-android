package doubleslash05.mini.team11.ui.tutorial

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.icaksama.rapidsphinx.RapidPreparationListener
import com.icaksama.rapidsphinx.RapidSphinx
import com.icaksama.rapidsphinx.RapidSphinxListener
import doubleslash05.mini.team11.R
import edu.cmu.pocketsphinx.Config
import kotlinx.android.synthetic.main.fragment_tutorial_page2.*
import kotlinx.android.synthetic.main.fragment_tutorial_page3.*
import kotlinx.android.synthetic.main.fragment_tutorial_page4.*

class TutorialActivity : AppCompatActivity(), RapidSphinxListener {
    private val stopKeyword = "stop"
    private val nextKeyword = "next"
    private val pauseKeyword = "pause"
    private var vpPager: ViewPager? = null
    private var customTab: TabLayout? = null
    private var rapidSphinx: RapidSphinx? = null
    private val tutorialFragment2: TutorialFragment2 = TutorialFragment2()
    private val tutorialFragment3: TutorialFragment3 = TutorialFragment3()
    private val tutorialFragment4: TutorialFragment4 = TutorialFragment4()
    override fun rapidSphinxDidStop(reason: String, code: Int) {
        rapidSphinx!!.stop()
    }

    // most important method
    override fun rapidSphinxFinalResult(
        result: String,
        hypArr: List<String>,
        scores: List<Double>
    ) {
        when (result) {
            stopKeyword -> {
                tvResult.setText(result)
                vpPager?.setCurrentItem(2)
                rapidSphinx?.startRapidSphinx(10000)
            }
            nextKeyword -> {
                tvResult2.setText(result)
                vpPager?.setCurrentItem(3)
                rapidSphinx?.startRapidSphinx(10000)
            }
            pauseKeyword -> {
                tvResult3.setText(result)
                vpPager?.setCurrentItem(4)
                rapidSphinx?.startRapidSphinx(10000)
            }
        }
    }

    override fun rapidSphinxPartialResult(partialResult: String) {

    }
    override fun rapidSphinxUnsupportedWords(words: List<String>) {

    }
    override fun rapidSphinxDidSpeechDetected() {
        Log.d("cycle", "detected")
    }

    var adapterViewPager: FragmentPagerAdapter? = null

    class TutorialPageAdapter(fragmentManager: FragmentManager?) : FragmentPagerAdapter(
        fragmentManager!!
    ) {
        override fun getItemPosition(`object`: Any): Int {
            return super.getItemPosition(`object`)
        }

        override fun getCount(): Int {
            return PAGE_NUN
        }

        override fun getItem(position: Int): Fragment {
            return when (position) {
                0 -> {
                    TutorialFragment1()
                }
                1 -> {
                    TutorialFragment2()
                }
                2 -> {
                    TutorialFragment3()
                }
                3 -> {
                    TutorialFragment4()
                }
                4 -> {
                    TutorialFragment5()
                }
                else -> TutorialFragment1()
            }
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return null
        }

        companion object {
            private const val PAGE_NUN = 5
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tutorial)
        Log.d("cycle", "onCreate")
        rapidSphinx = RapidSphinx(this)
        rapidSphinx!!.addListener(this)
        if (isPermissionsGranted) try {
            rapidSphinx!!.prepareRapidSphinx(object : RapidPreparationListener {
                override fun rapidPreExecute(config: Config) {
                    rapidSphinx!!.isRawLogAvailable = true
                    config.setString("-logfn", "/dev/null")
                    config.setBoolean("-verbose", true)
                }

                override fun rapidPostExecute(isSuccess: Boolean) {
                    vpPager!!.currentItem = 1
                }
            })
        } catch (e: Exception) {
            println(e)
        }
        vpPager = findViewById<View>(R.id.vpPager) as ViewPager
        customTab = findViewById<View>(R.id.customTab) as TabLayout
        customTab!!.setupWithViewPager(vpPager, true)
        adapterViewPager = TutorialPageAdapter(supportFragmentManager)
        vpPager!!.adapter = adapterViewPager
    }

    override fun onResume() {
        super.onResume()
        Log.d("cycle", "onResume")
        val oovwords = arrayOf("next", "stop")
        rapidSphinx!!.updateVocabulary(
            stopKeyword,
            oovwords
        ) { rapidSphinx!!.startRapidSphinx(10000) }
    }

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