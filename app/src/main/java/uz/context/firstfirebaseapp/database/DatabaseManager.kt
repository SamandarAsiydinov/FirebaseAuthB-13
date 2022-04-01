package uz.context.firstfirebaseapp.database

import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import uz.context.firstfirebaseapp.model.Post

class DatabaseManager {
    companion object {
        private var database = FirebaseDatabase.getInstance().reference
        private var reference = database.child("posts")

        fun startPost(post: Post, handler: DatabaseHandler) {
            val key = reference.push().key ?: return
            post.id = key
            reference.child(key).setValue(post)
                .addOnSuccessListener {
                    handler.onSuccess()
                }.addOnFailureListener {
                    handler.onError()
                }
        }

        fun apiLoadPosts(handler: DatabaseHandler) {
            reference.addValueEventListener(object : ValueEventListener {
                var posts = ArrayList<Post>()
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        for (snap in snapshot.children) {
                            val post: Post? = snap.getValue(Post::class.java)
                            post.let {
                                posts.add(post!!)
                                Log.d("@@@@1111","this 1 == ")
                            }
                        }
                        handler.onSuccess(posts = posts)
                    } else {
                        handler.onSuccess(posts = ArrayList())
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    handler.onError()
                }
            })
        }

        fun apiDeletePost(post: Post, databaseHandler: DatabaseHandler) {
            val key = post.id
            reference.child(key!!).removeValue()
                .addOnSuccessListener {
                    databaseHandler.onSuccess()
                }.addOnFailureListener {
                    databaseHandler.onError()
                }
        }
    }
}