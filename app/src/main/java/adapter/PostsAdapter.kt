package adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.praktika1.Post
import com.example.praktika1.R
import com.example.praktika1.databinding.ActivityCardPostBinding

typealias OnLikeListener = (post: Post) -> Unit
typealias OnShareListener = (post: Post) -> Unit

class PostsAdapter(
    private val onLikeListener: OnLikeListener,
    private val onShareListener: OnShareListener) : ListAdapter<Post, PostViewHolder>(PostDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = ActivityCardPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(binding, onLikeListener, onShareListener)
    }
    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = getItem(position)
        holder.bind(post)
    }
}
class PostViewHolder(
    private val binding: ActivityCardPostBinding,
    private val onLikeListener: OnLikeListener,
    private val onShareListener: OnShareListener
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(post: Post) {
        binding.apply {
            author.text = post.author
            published.text = post.published
            mainText.text = post.content
            textView5.text = post.likes.toString()
            textView6.text = post.share.toString()
            imageButton7.setImageResource(
                if (post.likedByMe) R.drawable.like_svgrepo_com__1_ else R.drawable.like_placeholder_svgrepo_com
            )
            if (post.likedByMe) {
                imageButton7.setImageResource(R.drawable.like_svgrepo_com__1_)
            }
            imageButton7.setOnClickListener{
                onLikeListener(post)
            }
            imageButton8.setOnClickListener{
                onShareListener(post)
            }
            when {
                post.likes in 1000..999999 -> textView5.text = "${post.likes / 1000}K"
                post.likes < 1000 -> textView5.text = post.likes.toString()
                else -> textView5.text = String.format("%.1fM", post.likes.toDouble() / 1000000)
            }
            textView6.text = post.share.toString()
            when {
                post.share < 1000 -> textView6.text = post.share.toString()
                post.share in 1000..999999 -> textView6.text = "${post.share / 1000}K"
                else -> textView6.text = String.format(
                    "%.1fM", post.share.toDouble() / 1000000
                )
            }
        }
    }

}
class PostDiffCallback : DiffUtil.ItemCallback<Post>() {
    override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem == newItem
    }
}