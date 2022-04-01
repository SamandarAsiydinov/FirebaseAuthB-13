package uz.context.firstfirebaseapp

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import uz.context.firstfirebaseapp.adapter.PostAdapter
import uz.context.firstfirebaseapp.database.DatabaseHandler
import uz.context.firstfirebaseapp.database.DatabaseManager
import uz.context.firstfirebaseapp.manager.AuthManager
import uz.context.firstfirebaseapp.model.Post
import uz.context.firstfirebaseapp.util.Extensions.toast

class MainActivity : BaseActivity() {
    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        initViews()
    }

    private fun initViews() {
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = GridLayoutManager(this, 1)

        val fabBtn = findViewById<FloatingActionButton>(R.id.floatingBtn)
        fabBtn.setOnClickListener {
            callCreateActivity()
        }

        apiLoadPosts()
    }

    fun apiLoadPosts() {
        showLoading(this)
        DatabaseManager.apiLoadPosts(object : DatabaseHandler {
            override fun onSuccess(post: Post?, posts: ArrayList<Post>) {
                dismissLoading()
                refreshAdapter(posts)
            }

            override fun onError() {
                dismissLoading()
            }
        })
    }

    fun apiDeletePost(post: Post) {
        DatabaseManager.apiDeletePost(post, object: DatabaseHandler{
            override fun onSuccess(post: Post?, posts: ArrayList<Post>) {
                apiLoadPosts()
            }

            override fun onError() {
                toast("Error")
            }
        })
    }

    private var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data: Intent? = result.data
            apiLoadPosts()
        }
    }

    private fun callCreateActivity() {
        val intent = Intent(context, CreateActivity::class.java)
        resultLauncher.launch(intent)
    }

    fun refreshAdapter(posts: ArrayList<Post>) {
        val adapter = PostAdapter(this, posts)
        recyclerView.adapter = adapter
    }
}