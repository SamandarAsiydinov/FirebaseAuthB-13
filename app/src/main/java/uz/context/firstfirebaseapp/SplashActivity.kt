package uz.context.firstfirebaseapp

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import androidx.appcompat.app.AppCompatActivity
import uz.context.firstfirebaseapp.manager.AuthManager

class SplashActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        initViews()

    }

    private fun initViews() {
        val countDownTimer = object : CountDownTimer(2000, 2000) {
            override fun onTick(p0: Long) {}

            override fun onFinish() {
                if (AuthManager.isSignedIn()) {
                    callMainActivity(this@SplashActivity)
                } else {
                    callSignInActivity(this@SplashActivity)
                }
            }
        }
        countDownTimer.start()
    }
}