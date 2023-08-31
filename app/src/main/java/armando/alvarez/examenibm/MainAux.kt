package armando.alvarez.examenibm

import armando.alvarez.examenibm.data.model.Book

interface MainAux {
    fun showButtons(boolean: Boolean)
    fun getBookSelected() : Book?
}