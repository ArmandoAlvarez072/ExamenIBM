package armando.alvarez.examenibm.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import armando.alvarez.examenibm.data.model.BooksResponse
import armando.alvarez.examenibm.data.util.Network
import armando.alvarez.examenibm.data.util.Resource
import armando.alvarez.examenibm.domain.usecase.GetBooksUseCase
import armando.alvarez.examenibm.domain.usecase.GetFilteredBooksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class BooksViewModel @Inject constructor(
    private val application: Application,
    private val getBooksUseCase: GetBooksUseCase,
    private val getFilteredBooksUseCase: GetFilteredBooksUseCase
) : ViewModel() {

    var booksResponse: MutableLiveData<Resource<BooksResponse>> = MutableLiveData()

    fun getBooks(title: String, page: Int, results: Int) = viewModelScope.launch(
        Dispatchers.IO
    ) {
        booksResponse.postValue(Resource.Loading())
        try {
            if (Network.isNetworkAvailable(application)) {
                val apiResult = getBooksUseCase.execute(title, page, results)
                booksResponse.postValue(apiResult)
            } else {
                booksResponse.postValue(Resource.Error("No hay encontró conexión a internet"))
            }
        } catch (e: Exception) {
            booksResponse.postValue(Resource.Error(e.message.toString()))
        }
    }

    fun getFilteredBooks(title: String, filter: String, page: Int, results: Int) =
        viewModelScope.launch(
            Dispatchers.IO
        ) {
            booksResponse.postValue(Resource.Loading())
            try {
                if (Network.isNetworkAvailable(application)) {
                    val apiResult = getFilteredBooksUseCase.execute(title, filter, page, results)
                    booksResponse.postValue(apiResult)
                } else {
                    booksResponse.postValue(Resource.Error("No hay encontró conexión a internet"))
                }
            } catch (e: Exception) {
                booksResponse.postValue(Resource.Error(e.message.toString()))
            }
        }


}