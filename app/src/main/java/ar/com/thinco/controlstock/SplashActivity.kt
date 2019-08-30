package ar.com.thinco.controlstock

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils.loadAnimation
import kotlinx.android.synthetic.main.activity_splash.*
import android.content.Intent
import android.os.Handler





class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val myFadeInAnimation = loadAnimation(this, R.anim.fade_in)
        imgLogoBlanco.startAnimation(myFadeInAnimation)

        Handler().postDelayed({
            val i = Intent(this, MainActivity::class.java)
            i.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(i)
        }, 4000)
    }
}
