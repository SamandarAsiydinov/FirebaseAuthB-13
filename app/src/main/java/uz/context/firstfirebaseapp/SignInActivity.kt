package uz.context.firstfirebaseapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import uz.context.firstfirebaseapp.databinding.ActivitySignInBinding
import uz.context.firstfirebaseapp.manager.AuthHandler
import uz.context.firstfirebaseapp.manager.AuthManager
import uz.context.firstfirebaseapp.util.Extensions.toast

class SignInActivity : BaseActivity() {

    private lateinit var binding: ActivitySignInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()

    }

    private fun initViews() {
        binding.bSignin.setOnClickListener {
            val email = binding.etEmail.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()
            firebaseSignIn(email, password)
        }
        binding.tvSignup.setOnClickListener {
            callSignUpActivity()
        }
    }

    private fun firebaseSignIn(email: String, password: String) {
        showLoading(this)
        AuthManager.signIn(email, password, object : AuthHandler {
            override fun onSuccess() {
                dismissLoading()
                toast("Sign in success")
                callMainActivity(context)
            }

            override fun onError(exception: Exception?) {
                dismissLoading()
                toast("Sign in failed $exception")
            }
        })
    }

    private fun callSignUpActivity() {
        val intent = Intent(this, SignUpActivity::class.java)
        startActivity(intent)
        finish()
    }
}