import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.posts.test.R
import com.posts.test.models.data_classes.Data

class PostAdapter(private val onItemClick: (Data) -> Unit) :
    RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    private val posts = mutableListOf<Data>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_post, parent, false)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = posts[position]
        holder.bind(post)
        holder.itemView.setOnClickListener { onItemClick(post) }
    }

    override fun getItemCount(): Int = posts.size

    fun updatePosts(newPosts: List<Data>) {
        posts.addAll(newPosts)
        notifyDataSetChanged()
    }

    class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(post: Data) {
            itemView.findViewById<TextView>(R.id.postId).text = post.id.toString()
            itemView.findViewById<TextView>(R.id.postFirstName).text = post.first_name
            itemView.findViewById<TextView>(R.id.postFirstName).text = post.last_name
            itemView.findViewById<TextView>(R.id.postLastEmail).text = post.email
        }
    }
}