package doubleslash05.mini.team11.ui.splash

import android.animation.Animator
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import doubleslash05.mini.team11.App
import doubleslash05.mini.team11.R
import doubleslash05.mini.team11.ui.main.HomeActivity
import doubleslash05.mini.team11.ui.tutorial.TutorialActivity
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        animationview_splash.addAnimatorListener(object : Animator.AnimatorListener{
            override fun onAnimationRepeat(animation: Animator?) {

            }

            override fun onAnimationEnd(animation: Animator?) {
                val intent = Intent(application, if(App.prefs.isShowTutorial) TutorialActivity::class.java else HomeActivity::class.java)
                startActivity(intent)
                finish()
            }

            override fun onAnimationCancel(animation: Animator?) {

            }

            override fun onAnimationStart(animation: Animator?) {

            }
        })

//        animationview_splash.setAnimation("lottie_splash.json")
//        animationview_splash.playAnimation()

//        Handler(Looper.getMainLooper()).postDelayed({
//            val intent = Intent(application, TutorialActivity::class.java)
//            startActivity(intent)
//            finish()
//        }, 3000)
    }


}