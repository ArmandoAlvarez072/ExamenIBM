package armando.alvarez.examenibm.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import armando.alvarez.examenibm.data.model.Book
import armando.alvarez.examenibm.databinding.ItemBookBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

class BooksAdapter : RecyclerView.Adapter<BooksAdapter.ViewHolder>() {

    private lateinit var context: Context

    private val callback = object : DiffUtil.ItemCallback<Book>() {
        override fun areItemsTheSame(oldItem: Book, newItem: Book): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Book, newItem: Book): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, callback)

    inner class ViewHolder(
        private val binding: ItemBookBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(book: Book) {

            binding.tvAuthor.text = book.volumeInfo?.getAuthors()
            binding.tvTitle.text = book.volumeInfo?.title
            binding.tvDate.text = book.volumeInfo?.publishedDate

            Glide.with(binding.imgBook.context)
                .load(book.volumeInfo?.getImageLink())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(binding.imgBook)

            binding.root.setOnClickListener {
                onItemClickListener?.let {
                    it(book)
                }
            }
        }
    }

    private var onItemClickListener: ((Book) -> Unit)? = null

    fun setOnItemClickListener(listener: (Book) -> Unit) {
        onItemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val binding = ItemBookBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = differ.currentList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val book = differ.currentList[position]
        holder.bind(book)
    }

}