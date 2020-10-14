package doubleslash05.mini.team11.ui.tutorial

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.icaksama.rapidsphinx.RapidPreparationListener
import com.icaksama.rapidsphinx.RapidSphinx
import com.icaksama.rapidsphinx.RapidSphinxListener
import doubleslash05.mini.team11.R
import doubleslash05.mini.team11.ui.base.BaseActivity
import doubleslash05.mini.team11.ui.base.BaseFragment
import doubleslash05.mini.team11.ui.main.HomeActivity
import edu.cmu.pocketsphinx.Config
import kotlinx.android.synthetic.main.activity_tutorial.*
import org.jetbrains.anko.startActivity

class TutorialActivity : BaseActivity(), RapidSphinxListener {
    companion object {
        private const val pauseKeyword = "darcy"
        private const val stopKeyword = "stop"
        private const val stopKeyword2 = "region"
        private const val nextKeyword = "daum"
    }

    private val rapidSphinx: RapidSphinx by lazy { RapidSphinx(this) }
    private val adapterViewPager: FragmentPagerAdapter = TutorialPageAdapter(supportFragmentManager)
    private val updateHandler = Handler()
    private val runnable = Runnable {
        updateDisplay()
    }

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
            stopKeyword2 -> {
                viewpager_tutorial.currentItem = 1
                btnSkip.setText("Skip")
            }
            nextKeyword -> {
                viewpager_tutorial.currentItem = 2
                btnSkip.setText("Skip")
                btnSkip.setBackgroundColor(Color.parseColor("#FF9E00"))
            }
            pauseKeyword -> {
                viewpager_tutorial.currentItem = 3
                btnSkip.isEnabled = true
                btnSkip.setText("시작하기")
                btnSkip.setBackgroundColor(Color.parseColor("#FF9E00"))

            }

        }


    }

    override fun rapidSphinxUnsupportedWords(words: List<String>) {

    }

    override fun rapidSphinxDidSpeechDetected() {

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tutorial)
        btnSkip.isEnabled = false
        updateHandler.postDelayed(runnable, 5000)

        btnSkip.setOnClickListener {

            if (viewpager_tutorial.currentItem == 0) {
                btnSkip.setText("Skip")
            }
            else if (viewpager_tutorial.currentItem == 1) {
                btnSkip.setText("Skip")


            } else if (viewpager_tutorial.currentItem == 2) {
                btnSkip.setText("시작하기")
                btnSkip.setBackgroundColor(Color.parseColor("#FF9E00"))

            } else if (viewpager_tutorial.currentItem == 3) {
                btnSkip.isEnabled = true
                rapidSphinx.stop()
                startActivity<HomeActivity>()

            }
            viewpager_tutorial.currentItem += 1
            if (viewpager_tutorial.currentItem == 0 || viewpager_tutorial.currentItem == 1 || viewpager_tutorial.currentItem == 2){
                btnSkip.setBackgroundColor(Color.parseColor("#707070"))
                updateHandler.postDelayed(runnable, 5000)
                btnSkip.isEnabled = false


            }

        }
        customTab.setupWithViewPager(viewpager_tutorial, true)
        customTab.getTabAt(3).isVi
        viewpager_tutorial.adapter = adapterViewPager

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
            stopKeyword,
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

    private fun updateDisplay() {
        btnSkip.isEnabled = true
        btnSkip.setBackgroundColor(Color.parseColor("#FF9E00"))

    }

    class TutorialPageAdapter(fragmentManager: FragmentManager?) : FragmentPagerAdapter(
        fragmentManager!!
    ) {

        private val PAGE_NUMBER = listOf<BaseFragment>(
            Tutorial1Fragment(),
            Tutorial2Fragment(),
            Tutorial3Fragment(),
            Tutorial4Fragment()
        )



        override fun getItemPosition(`object`: Any): Int {
            return super.getItemPosition(`object`)
        }

        override fun getCount(): Int {
            return PAGE_NUMBER.size
        }

        override fun getItem(position: Int): Fragment {
            return PAGE_NUMBER[position]
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return null
        }


    }


}