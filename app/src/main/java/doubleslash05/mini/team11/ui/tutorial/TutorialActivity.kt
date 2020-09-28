package doubleslash05.mini.team11.ui.tutorial

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.View
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

class TutorialActivity : AppCompatActivity(), RapidSphinxListener {
    private val stopKeyword = "stop"
    private val nextKeyword = "next"
    private val pauseKeyword = "pause"
    private var vpPager: ViewPager? = null
    private var customTab: TabLayout? = null
    private var rapidSphinx: RapidSphinx? = null
    override fun rapidSphinxDidStop(reason: String, code: Int) {
        Log.d("cycle", "rapid stopped")
        rapidSphinx!!.startRapidSphinx(10000)
    }

    // most important method
    override fun rapidSphinxFinalResult(
        result: String,
        hypArr: List<String>,
        scores: List<Double>
    ) {
        when (result) {
            stopKeyword -> {
                vpPager?.setCurrentItem(1)
            }
        }
    }

    override fun rapidSphinxPartialResult(partialResult: String) {

        if (partialResult == nextKeyword){
            vpPager?.setCurrentItem(2)
        } else if (partialResult == pauseKeyword) {
            vpPager?.setCurrentItem(3)
        }

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
                else -> TutorialFragment1()
            }
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return null
        }

        companion object {
            private const val PAGE_NUN = 4
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
        val oovwords = arrayOf("next", "pause")
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