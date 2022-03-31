package uz.context.firstfirebaseapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import uz.context.firstfirebaseapp.manager.AuthManager
import uz.context.firstfirebaseapp.util.Extensions.toast

class MainActivity: BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()

    }
    private fun initViews() {
        val btnSignOut: Button = findViewById(R.id.btnSignOut)
        btnSignOut.setOnClickListener {
            AuthManager.singOut()
            val intent = Intent(this,SignInActivity::class.java)
            startActivity(intent)
            finish()
            toast("Signed out")
        }
    }
}