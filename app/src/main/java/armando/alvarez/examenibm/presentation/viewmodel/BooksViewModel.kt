package armando.alvarez.examenibm.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import armando.alvarez.examenibm.data.model.Book
import armando.alvarez.examenibm.data.model.BooksResponse
import armando.alvarez.examenibm.data.util.Network
import armando.alvarez.examenibm.data.util.Resource
import armando.alvarez.examenibm.domain.usecase.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class BooksViewModel @Inject constructor(
    private val application: Application,
    private val getBooksUseCase: GetBooksUseCase,
    private val getFilteredBooksUseCase: GetFilteredBooksUseCase,
    private val getSavedVolumeInfoUseCase: GetSavedVolumeInfoUseCase,
    private val getSavedSaleInfoUseCase: GetSavedSaleInfoUseCase,
    private val deleteBookUseCase: DeleteBookUseCase,
    private val saveBooksUseCase: SaveBookUseCase,
    private val isSavedUseCase: IsSavedUseCase
) : ViewModel() {

    var booksResponse: MutableLiveData<Resource<BooksResponse>> = MutableLiveData()

    fun getBooks(title: String, startIndex: Int, results: Int) = viewModelScope.launch(
        Dispatchers.IO
    ) {
        booksResponse.postValue(Resource.Loading())
        try {
            if (Network.isNetworkAvailable(application)) {
                val apiResult = getBooksUseCase.execute(title, startIndex, results)
                booksResponse.postValue(apiResult)
            } else {
                booksResponse.postValue(Resource.Error("No hay encontró conexión a internet"))
            }
        } catch (e: Exception) {
            booksResponse.postValue(Resource.Error(e.message.toString()))
        }
    }

    fun getFilteredBooks(title: String, filter: String, startIndex: Int, results: Int) =
        viewModelScope.launch(
            Dispatchers.IO
        ) {
            booksResponse.postValue(Resource.Loading())
            try {
                if (Network.isNetworkAvailable(application)) {
                    val apiResult =
                        getFilteredBooksUseCase.execute(title, filter, startIndex, results)
                    booksResponse.postValue(apiResult)
                } else {
                    booksResponse.postValue(Resource.Error("No hay encontró conexión a internet"))
                }
            } catch (e: Exception) {
                booksResponse.postValue(Resource.Error(e.message.toString()))
            }
        }

    fun deleteBook(id: String) = viewModelScope.launch(Dispatchers.IO) {
        deleteBookUseCase.execute(id)
    }

    fun saveBook(book: Book) = viewModelScope.launch(Dispatchers.IO) {
        saveBooksUseCase.execute(book)
    }

    fun getSavedSaleInfo() = liveData {
        withContext(Dispatchers.IO) {
            getSavedSaleInfoUseCase.execute().collect {
                emit(it)
            }
        }
    }

    fun getSavedVolumeInfo() = liveData {
        withContext(Dispatchers.IO) {
            getSavedVolumeInfoUseCase.execute().collect {
                emit(it)
            }
        }
    }
    fun isSaved(id: String) = liveData {
        withContext(Dispatchers.IO) {
            isSavedUseCase.execute(id).collect {
                emit(it)
            }
        }
    }

}