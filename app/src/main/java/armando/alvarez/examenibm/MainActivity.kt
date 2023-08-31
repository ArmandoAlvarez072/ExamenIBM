package armando.alvarez.examenibm

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.fragment.app.commit
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.GridLayoutManager
import armando.alvarez.examenibm.data.model.Book
import armando.alvarez.examenibm.data.util.Resource
import armando.alvarez.examenibm.databinding.ActivityMainBinding
import armando.alvarez.examenibm.presentation.adapter.BooksAdapter
import armando.alvarez.examenibm.presentation.viewmodel.BooksViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : BaseActivity(), MainAux {
    private lateinit var binding: ActivityMainBinding

    private var searchSaved = false

    @Inject
    lateinit var adapter: BooksAdapter

    private var booksList: List<Book>? = mutableListOf()
    private var bookSelected: Book? = null

    private var results = 10
    private var page = 1
    private var filter = ""

    val viewModel: BooksViewModel by viewModels()

    private val filterValues: Array<String> by lazy {
        resources.getStringArray(R.array.filter_value)
    }

    private val filterText: Array<String> by lazy {
        resources.getStringArray(R.array.filter_text)
    }

    private val resultsArray: Array<Int> by lazy {
        resources.getIntArray(R.array.number_items).toTypedArray()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        configListeners()

        configAdapter()
        configRecyclerView()

        configACTV()

    }

    private fun configACTV() {
        val adapter = ArrayAdapter(
            this, android.R.layout.simple_dropdown_item_1line, filterText
        )
        binding.actvFilter.setAdapter(adapter)

        val resultsAdapter =
            ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, resultsArray)
        binding.actvResults.setAdapter(resultsAdapter)
    }

    private fun configListeners() {
        binding.btnSearch.setOnClickListener {
            getBooks()
        }

        binding.actvFilter.setOnItemClickListener { adapterView, view, position, id ->
            filter = filterValues[position]
            getBooks()
        }

        binding.actvFilter.setOnItemClickListener { adapterView, view, position, id ->
            results = resultsArray[position]
            getBooks()
        }

        binding.imgCheck.setOnClickListener {
            if (searchSaved) {
                binding.imgCheck.setImageResource(R.drawable.uncheck)
                if (!booksList.isNullOrEmpty()) {
                    binding.tilResults.visibility = View.VISIBLE
                    binding.tilFilter.visibility = View.VISIBLE
                }
            } else {
                binding.imgCheck.setImageResource(R.drawable.check)
                binding.tilResults.visibility = View.GONE
                binding.tilFilter.visibility = View.GONE
            }

            configConstraintsRv()

            searchSaved = !searchSaved
        }
    }

    private fun getFavoritesBooks() {
        TODO("Not yet implemented")
    }

    private fun configConstraintsRv() {
        val constraintLayout = binding.container
        val constraintSet = ConstraintSet()
        constraintSet.clone(constraintLayout)
        if (searchSaved) {

            constraintSet.connect(
                R.id.rvComments,
                ConstraintSet.TOP,
                R.id.tilResults,
                ConstraintSet.BOTTOM
            )
        } else {
            constraintSet.connect(
                R.id.rvComments,
                ConstraintSet.TOP,
                R.id.imgCheck,
                ConstraintSet.BOTTOM
            )
        }

        constraintSet.applyTo(constraintLayout)

    }

    private fun configAdapter() {
        adapter.setOnItemClickListener {

            val fragment = BookFragment()

            supportFragmentManager.commit {
                setCustomAnimations(
                    R.anim.slide_in,
                    R.anim.fade_out,
                    R.anim.fade_in,
                    R.anim.slide_out
                )

                add(R.id.containerMain, fragment)
                addToBackStack(null)
            }

            bookSelected = it

            showButtons(false)

        }
    }

    private fun configRecyclerView() {
        binding.rvComments.apply {
            adapter = this@MainActivity.adapter
            layoutManager = GridLayoutManager(
                this@MainActivity,
                1,
                GridLayoutManager.VERTICAL,
                false
            )
        }
    }

    private fun getBooks() {
        if (!searchSaved) {
            if (binding.etSearch.text.isNullOrEmpty()) {
                showAlert(
                    getString(R.string.text_dialog_alert),
                    getString(R.string.text_empty),
                    null
                )
            } else {
                viewModel.booksResponse = MutableLiveData()

                if (filter.isEmpty()) {
                    viewModel.getBooks(binding.etSearch.text.toString(), page, results)
                } else {
                    viewModel.getFilteredBooks(binding.etSearch.text.toString(), filter, page, results)
                }

                viewModel.booksResponse.observeOnce(this) { response ->
                    when (response) {
                        is Resource.Success -> {
                            response.data?.let {
                                hideLoading()
                                if (it.books?.size!! > 0) {
                                    booksList = it.books
                                    adapter.differ.submitList(it.books)
                                    binding.tilResults.visibility = View.VISIBLE
                                    binding.tilFilter.visibility = View.VISIBLE
                                } else {
                                    booksList = mutableListOf()
                                    adapter.differ.submitList(booksList)
                                    Handler(Looper.getMainLooper()).postDelayed({
                                        showAlert(
                                            getString(R.string.text_dialog_alert),
                                            getString(R.string.text_empty_search),
                                            null
                                        )
                                    }, 200)
                                }
                            }
                        }

                        is Resource.Error -> {
                            booksList = mutableListOf()
                            adapter.differ.submitList(booksList)

                            response.message?.let {
                                hideLoading()
                                Handler(Looper.getMainLooper()).postDelayed({
                                    showAlert(
                                        getString(R.string.text_dialog_alert),
                                        getString(R.string.text_error),
                                        null
                                    )
                                }, 200)

                            }
                        }

                        is Resource.Loading -> {
                            showLoading()
                        }
                    }
                }

            }
        } else {
            getFavoritesBooks()
        }
    }


    override fun showButtons(boolean: Boolean) {
        binding.btnSearch.visibility = if (boolean) View.VISIBLE else View.GONE
        binding.btnNext.visibility = if (boolean) View.VISIBLE else View.GONE
    }

    override fun getBookSelected(): Book? = bookSelected
}