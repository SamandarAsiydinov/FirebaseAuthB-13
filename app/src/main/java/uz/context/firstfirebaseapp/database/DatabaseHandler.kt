package uz.context.firstfirebaseapp.database

import uz.context.firstfirebaseapp.model.Post

interface DatabaseHandler {
    fun onSuccess(post: Post? = null, posts: ArrayList<Post> = ArrayList())
    fun onError()
}