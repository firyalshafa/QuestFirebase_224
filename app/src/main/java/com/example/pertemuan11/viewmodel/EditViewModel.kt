package com.example.pertemuan11.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pertemuan11.modeldata.DetailSiswa
import com.example.pertemuan11.modeldata.UIStateSiswa
import com.example.pertemuan11.modeldata.toDataSiswa
import com.example.pertemuan11.modeldata.toUiStateSiswa
import com.example.pertemuan11.repositori.RepositorySiswa
import com.example.pertemuan11.view.route.DestinasiDetail
import kotlinx.coroutines.launch

class EditViewModel(savedStateHandle: SavedStateHandle, private val repositorySiswa:
RepositorySiswa
): ViewModel() {

    var uiStateSiswa by mutableStateOf(UIStateSiswa())
        private set

    private val idSiswa: Long =
        savedStateHandle.get<String>(DestinasiDetail.itemIdArg)?.toLong()
            ?: error("idSiswa tidak ditemukan di SavedStateHandle")
    init {
        viewModelScope.launch {
            uiStateSiswa = repositorySiswa.getSatuSiswa(idSiswa)!!
                .toUiStateSiswa(true)
        }
    }
    private fun validasiInput(uiState: DetailSiswa = uiStateSiswa.detailSiswa ): Boolean {
        return with(uiState) {
            nama.isNotBlank() && alamat.isNotBlank() && telpon.isNotBlank()
        }
    }
    suspend fun editSatuSiswa(){
        if (validasiInput(uiStateSiswa.detailSiswa)){
            try {
                repositorySiswa.editSatuSiswa(idSiswa,uiStateSiswa.detailSiswa.toDataSiswa
                    ())
                println("Update Sukses: $idSiswa")
            } catch (e: Exception) {
                println("Update Error: ${e.message}")
            }
        }
    }

}