package uz.context.firstfirebaseapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import uz.context.firstfirebaseapp.databinding.ActivitySignInBinding
import uz.context.firstfirebaseapp.databinding.ActivitySignUpBinding
import uz.context.firstfirebaseapp.manager.AuthHandler
import uz.context.firstfirebaseapp.manager.AuthManager
import uz.context.firstfirebaseapp.util.Extensions.toast

class SignUpActivity : BaseActivity() {

    private lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()

    }
    private fun initViews() {
        binding.bSignup.setOnClickListener {
            val email = binding.etEmail.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()
            firebaseSignUp(email, password)
        }
        binding.tvSignin.setOnClickListener {
            finish()
        }
    }

    private fun firebaseSignUp(email: String, password: String) {
        AuthManager.signUp(email,password, object : AuthHandler {
            override fun onSuccess() {
                toast("Signed up successfully")
                callMainActivity(context)
            }

            override fun onError(exception: Exception?) {
                toast("Signed up failed $exception")
            }
        })
    }

    private fun callSignUpActivity() {
        val intent = Intent(this, SignUpActivity::class.java)
        startActivity(intent)
        finish()
    }
}