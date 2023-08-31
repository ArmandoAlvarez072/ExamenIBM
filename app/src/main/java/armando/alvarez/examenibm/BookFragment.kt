package armando.alvarez.examenibm

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import armando.alvarez.examenibm.databinding.FragmentBookBinding
import armando.alvarez.examenibm.presentation.viewmodel.BooksViewModel
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy


class BookFragment : Fragment() {
    private var binding: FragmentBookBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBookBinding.inflate(inflater, container, false)
        binding?.let {
            return it.root
        }

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setListeners()
        getBook()
    }

    private fun setListeners() {
        binding?.btnBack?.setOnClickListener {
            goBack()
        }
    }

    private fun getBook() {
        val book = (activity as? MainAux)?.getBookSelected()

        book?.let { book ->

            binding?.let {
                var authors = ""

                book.volumeInfo?.authors?.forEach {
                    authors += "$it, "
                }

                it.tvAuthor.text =
                    if (!authors.isEmpty()) authors.substring(0, authors.length - 2) else ""

                it.tvDescription.text = book.volumeInfo?.description
                it.tvPrice.text =
                    if (book.saleInfo?.listPrice?.amount == null ||
                        book.saleInfo?.listPrice?.currencyCode.isNullOrEmpty()
                    ) ""
                    else
                        activity?.getString(
                            R.string.text_price,
                            book.saleInfo?.listPrice?.amount,
                            book.saleInfo?.listPrice?.currencyCode
                        )

                it.tvSubtitle.text = book.volumeInfo?.subtitle
                it.tvTitle.text = book.volumeInfo?.title

                Glide.with(it.imgBook.context)
                    .load(book.volumeInfo?.imageLinks?.thumbnail)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(it.imgBook)

                it.btnBuy.setOnClickListener {

                    if (book.saleInfo?.buyLink.isNullOrEmpty()) {
                        Toast.makeText(
                            activity,
                            activity?.getString(R.string.toast_no_link),
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        val browserIntent =
                            Intent(Intent.ACTION_VIEW, Uri.parse(book.saleInfo?.buyLink))

                        startActivity(browserIntent)
                    }

                }
            }
        }
    }

    private fun goBack() {
        activity?.onBackPressedDispatcher?.onBackPressed()
    }

    override fun onDestroyView() {

        (activity as? MainAux)?.showButtons(true)
        super.onDestroyView()
        binding = null
    }
}