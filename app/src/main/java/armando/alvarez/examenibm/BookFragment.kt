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


@Suppress("NAME_SHADOWING")
class BookFragment : Fragment() {
    private var binding: FragmentBookBinding? = null
    private lateinit var viewModel: BooksViewModel

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
        viewModel = (activity as MainActivity).viewModel

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
            viewModel.isSaved(book.id!!)

            binding?.let {

                it.tvAuthor.text = book.volumeInfo?.getAuthors()

                it.tvDescription.text = book.volumeInfo?.description
                it.tvPrice.text =
                    activity?.getString(
                        R.string.text_price,
                        book.saleInfo?.getBookPrice()
                    )

                it.tvSubtitle.text = book.volumeInfo?.subtitle
                it.tvTitle.text = book.volumeInfo?.title

                Glide.with(it.imgBook.context)
                    .load(book.volumeInfo?.getImageLink())
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
                            Intent(Intent.ACTION_VIEW, Uri.parse(book.saleInfo?.buyLink.toString()))

                        startActivity(browserIntent)
                    }

                }

                viewModel.isSaved(book.id!!).observe(this) {
                    if (it > 0) {
                        binding!!.imgSave.setImageResource(R.drawable.saved)
                        binding!!.imgSave.setOnClickListener {

                            (activity as MainActivity).showAlertYesNo(
                                getString(R.string.text_dialog_alert),
                                getString(R.string.text_confirm_delete),
                                fun() {
                                    viewModel.deleteBook(book.id!!)
                                    binding!!.imgSave.setImageResource(R.drawable.not_saved)
                                    goBack()
                                },
                                null
                            )
                        }
                    } else {
                        binding!!.imgSave.setImageResource(R.drawable.not_saved)
                        binding!!.imgSave.setOnClickListener {
                            binding!!.imgSave.setImageResource(R.drawable.saved)
                            viewModel.saveBook(book)
                        }


                    }
                }

            }
        }
    }

    private fun goBack() {
        activity?.onBackPressedDispatcher?.onBackPressed()
        (activity as MainActivity).getBooks()
    }

    override fun onDestroyView() {

        (activity as? MainAux)?.showButtons(true)
        super.onDestroyView()
        binding = null
    }
}