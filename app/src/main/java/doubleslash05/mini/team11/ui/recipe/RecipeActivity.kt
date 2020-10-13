package doubleslash05.mini.team11.ui.recipe

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.google.android.material.tabs.TabLayout
import com.icaksama.rapidsphinx.RapidPreparationListener
import com.icaksama.rapidsphinx.RapidSphinx
import com.icaksama.rapidsphinx.RapidSphinxListener
import doubleslash05.mini.team11.App
import doubleslash05.mini.team11.R
import doubleslash05.mini.team11.model.RecipeModel
import doubleslash05.mini.team11.model.data.RecipeData
import doubleslash05.mini.team11.model.data.RecipeVideoData
import doubleslash05.mini.team11.model.network.base.ApiStatus
import doubleslash05.mini.team11.ui.base.BaseActivity
import doubleslash05.mini.team11.ui.common.widget.recipevideo.RecipeVideoView
import doubleslash05.mini.team11.util.Log
import edu.cmu.pocketsphinx.Config
import kotlinx.android.synthetic.main.activity_recipe.*
import kotlinx.android.synthetic.main.view_recipe_video.*

class RecipeActivity : BaseActivity(),RapidSphinxListener, TabLayout.OnTabSelectedListener {
    private val infoFragment = RecipeInfoFragment()
    private val stepFragment = RecipeStepFragment()
    private val menuId by lazy { intent.getIntExtra(EXTRA_MENU_ID, -1) }
    private lateinit var data: RecipeData
    private val rapidSphinx: RapidSphinx by lazy { RapidSphinx(this) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe)

        rapidSphinx.addListener(this)


        button_recipevideo_start.setOnClickListener {
            rapidSphinx.startRapidSphinx(5000)
        }

            rapidSphinx.prepareRapidSphinx(object : RapidPreparationListener {
                override fun rapidPreExecute(config: Config) {
                    rapidSphinx.isRawLogAvailable = true
                    config.setString("-logfn", "/dev/null")
                    config.setBoolean("-verbose", true)
                }

                override fun rapidPostExecute(isSuccess: Boolean) {
                    Log.d("rapid2", "Executed!")
                }
            })

        videoview_receipe.setOnChangeSectionListener(object : RecipeVideoView.OnChangeSectionListener {
            override fun onChangeSection(index: Int) {
                stepFragment.setStep(index)
            }
        })

        tablayout_recipe.addOnTabSelectedListener(this)

        if (App.prefs.isShowVoiceGuide)
            GuideVoiceDialog().show(supportFragmentManager, null)

        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_recipe, stepFragment)
            .add(R.id.fragment_recipe, infoFragment)
            .commit()

        RecipeModel.getRecipeDetail(menuId).observe(this, Observer {
            when (it) {
                is ApiStatus.Success -> {
                    data = it.data
                    refresh()
                }
            }
        })


    }

    override fun rapidSphinxDidStop(reason: String?, code: Int) {
        Log.d("rapid", "stopped")
        rapidSphinx.startRapidSphinx(10000)
    }

    override fun rapidSphinxFinalResult(
        result: String?,
        hypArr: MutableList<String>?,
        scores: MutableList<Double>?
    ) {
    }

    override fun rapidSphinxPartialResult(partialResult: String?) {
        when (partialResult){
            stopKeyword2 -> videoview_receipe.prevSection()
            pauseKeyword -> videoview_receipe.replySction()
            nextKeyword -> videoview_receipe.nextSection()
        }
    }

    override fun rapidSphinxUnsupportedWords(words: MutableList<String>?) {
    }

    override fun rapidSphinxDidSpeechDetected() {
        Log.d("rapid", "detected")
    }

    @Override
    override fun onResume() {
        val oovwords = arrayOf("darcy", "daum", "region")
        super.onResume()
        rapidSphinx.updateVocabulary(stopKeyword, oovwords){
            Log.d("rapid", "updated!")

        }


    }

    override fun onTabSelected(tab: TabLayout.Tab) {
        val transaction = supportFragmentManager.beginTransaction()
        when (tab.position) {
            0 -> transaction.show(infoFragment).hide(stepFragment)
            1 -> transaction.hide(infoFragment).show(stepFragment)
        }
        transaction.commit()
    }

    override fun onTabReselected(tab: TabLayout.Tab) {

    }

    override fun onTabUnselected(tab: TabLayout.Tab) {

    }

    fun refresh() {
        val recipeVideoData = RecipeVideoData(data.videoUrl, data.ms)
        videoview_receipe.setData(recipeVideoData)

        textview_receipe_main.text = data.name
        textview_receipe_sub.text = data.descriptionShort

        infoFragment.setData(data)
        stepFragment.setData(data)
    }

    companion object {
        const val EXTRA_MENU_ID = "EXTRA_MENU_ID"
        const val EXTRA_MENU_DATA = "EXTRA_MENU_DATA"
         const val pauseKeyword = "darcy"
         const val stopKeyword = "stop"
         const val stopKeyword2 = "region"
         const val nextKeyword = "daum"
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