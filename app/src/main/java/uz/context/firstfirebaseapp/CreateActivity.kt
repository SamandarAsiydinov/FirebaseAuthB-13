package uz.context.firstfirebaseapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import androidx.appcompat.widget.AppCompatEditText
import com.google.android.material.button.MaterialButton
import uz.context.firstfirebaseapp.database.DatabaseHandler
import uz.context.firstfirebaseapp.database.DatabaseManager
import uz.context.firstfirebaseapp.model.Post
import uz.context.firstfirebaseapp.util.Extensions.toast

class CreateActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create)

        initViews()

    }

    private fun initViews() {
        val editTitle: AppCompatEditText = findViewById(R.id.editTitle)
        val editBody: AppCompatEditText = findViewById(R.id.editBody)
        val btnCrete: MaterialButton = findViewById(R.id.btnCreate)

        btnCrete.setOnClickListener {
            val title = editTitle.text.toString().trim()
            val body = editBody.text.toString().trim()
            val post = Post(title, body)
            storeDatabase(post)
            toast("Set")
        }
    }

    private fun storeDatabase(post: Post) {
        DatabaseManager.startPost(post, object : DatabaseHandler {
            override fun onSuccess(post: Post?, posts: ArrayList<Post>) {
                toast("Success")
                //dismissDialog()
                finishIntent()
            }

            override fun onError() {
                //dismissDialog()
                toast("Failure")
            }
        })
    }

    private fun finishIntent() {
        val returnIntent = intent
        setResult(RESULT_OK, returnIntent)
        finish()
    }
}