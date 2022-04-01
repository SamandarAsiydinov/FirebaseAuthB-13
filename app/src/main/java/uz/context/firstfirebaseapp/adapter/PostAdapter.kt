package uz.context.firstfirebaseapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import uz.context.firstfirebaseapp.MainActivity
import uz.context.firstfirebaseapp.R
import uz.context.firstfirebaseapp.model.Post

class PostAdapter(
    var activity: MainActivity,
    var items: ArrayList<Post>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_post_list, parent, false)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val post = items[position]
        if (holder is PostViewHolder) {
            holder.tvTitle.text = post.title?.uppercase()
            holder.tvBody.text = post.body
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
    inner class PostViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val tvTitle: TextView = view.findViewById(R.id.tvTitle)
        val tvBody: TextView = view.findViewById(R.id.tvBody)
        val llPost: LinearLayout = view.findViewById(R.id.llPost)
    }
}