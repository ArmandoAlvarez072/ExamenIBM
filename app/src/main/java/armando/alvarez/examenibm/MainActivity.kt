package armando.alvarez.examenibm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.GridLayoutManager
import armando.alvarez.examenibm.data.util.Resource
import armando.alvarez.examenibm.databinding.ActivityMainBinding
import armando.alvarez.examenibm.presentation.adapter.BooksAdapter
import armando.alvarez.examenibm.presentation.viewmodel.BooksViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var adapter: BooksAdapter

    private val viewModel: BooksViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        configListeners()

        configAdapter()
        configRecyclerView()

    }

    private fun configListeners() {
        binding.btnSearch.setOnClickListener {
            getBooks()
        }
    }

    private fun configAdapter() {
        adapter.setOnItemClickListener {
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
        viewModel.getBooks(binding.etSearch.text.toString(), 1, 10 )
        viewModel.booksResponse = MutableLiveData()
        viewModel.booksResponse.observeOnce(this) { response ->
            when (response) {
                is Resource.Success -> {
                    response.data?.let {
                        adapter.differ.submitList(it.books)
                        hideLoading()
                    }
                }

                is Resource.Error -> {
                    response.message?.let {
                        hideLoading()
                        Handler(Looper.getMainLooper()).postDelayed({
                            showAlert(getString(R.string.text_dialog_alert), it, null )
                        }, 200)

                    }
                }

                is Resource.Loading -> {
                    showLoading()
                }
            }
        }
    }
}