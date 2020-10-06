package doubleslash05.mini.team11.ui.tutorial

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.icaksama.rapidsphinx.RapidPreparationListener
import com.icaksama.rapidsphinx.RapidSphinx
import com.icaksama.rapidsphinx.RapidSphinxListener
import doubleslash05.mini.team11.R
import doubleslash05.mini.team11.ui.base.BaseActivity
import doubleslash05.mini.team11.ui.base.BaseFragment
import edu.cmu.pocketsphinx.Config
import kotlinx.android.synthetic.main.activity_tutorial.*

class TutorialActivity : BaseActivity(), RapidSphinxListener {
    companion object {
        private const val pauseKeyword = "darcy"
        private const val stopKeyword = "stop"
        private const val stopKeyword2 = "region"
        private const val nextKeyword = "daum"
    }

    private val rapidSphinx: RapidSphinx by lazy { RapidSphinx(this) }
    private val adapterViewPager: FragmentPagerAdapter = TutorialPageAdapter(supportFragmentManager)


    override fun rapidSphinxDidStop(reason: String, code: Int) {
        rapidSphinx.startRapidSphinx(10000)
    }

    override fun rapidSphinxFinalResult(
        result: String,
        hypArr: List<String>,
        scores: List<Double>
    ) {

    }

    override fun rapidSphinxPartialResult(partialResult: String) {

        when (partialResult) {
            stopKeyword2 -> viewpager_tutorial.setCurrentItem(1)
            nextKeyword -> viewpager_tutorial.setCurrentItem(2)
            pauseKeyword -> viewpager_tutorial.setCurrentItem(3)
        }


    }

    override fun rapidSphinxUnsupportedWords(words: List<String>) {

    }

    override fun rapidSphinxDidSpeechDetected() {

    }


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tutorial)

        customTab!!.setupWithViewPager(viewpager_tutorial, true)
        viewpager_tutorial!!.adapter = adapterViewPager

        Log.d("cycle", "onCreate")
        rapidSphinx.addListener(this)



        if (isPermissionsGranted)
            rapidSphinx.prepareRapidSphinx(object : RapidPreparationListener {
                override fun rapidPreExecute(config: Config) {
                    rapidSphinx.isRawLogAvailable = true
                    config.setString("-logfn", "/dev/null")
                    config.setBoolean("-verbose", true)
                }

                override fun rapidPostExecute(isSuccess: Boolean) {
                }
            })


    }

    override fun onResume() {
        super.onResume()
        Log.d("cycle", "onResume")
        val oovwords = arrayOf("darcy", "daum", "region")
        rapidSphinx.updateVocabulary(
            Companion.stopKeyword,
            oovwords
        ) { rapidSphinx.startRapidSphinx(10000) }
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

                0 -> Tutorial1Fragment()

                1 -> Tutorial2Fragment()

                2 -> Tutorial3Fragment()

                3 -> Tutorial4Fragment()

                else -> Tutorial1Fragment()
            }
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return null
        }

        companion object {
            private const val PAGE_NUN = 4
        }


    }


}