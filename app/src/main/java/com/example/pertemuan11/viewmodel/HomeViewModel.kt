package com.example.pertemuan11.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pertemuan11.modeldata.Siswa
import com.example.pertemuan11.repositori.RepositorySiswa
import kotlinx.coroutines.launch
import java.io.IOException

// Sealed interface untuk mendefinisikan status UI secara lengkap
sealed interface StatusUiSiswa {
    data class Success(val siswa: List<Siswa>) : StatusUiSiswa // Mengirim list siswa jika berhasil
    object Error : StatusUiSiswa // Status jika terjadi kesalahan
    object Loading : StatusUiSiswa // Status saat sedang mengambil data
}

class HomeViewModel(private val repositorySiswa: RepositorySiswa) : ViewModel() {

    // State untuk menyimpan status UI saat ini
    var statusUiSiswa: StatusUiSiswa by mutableStateOf(StatusUiSiswa.Loading)
        private set

    init {
        loadSiswa() // Memuat data pertama kali saat ViewModel dibuat
    }

    /**
     * Fungsi untuk mengambil data siswa dari Repository
     */
    fun loadSiswa() {
        viewModelScope.launch {
            statusUiSiswa = StatusUiSiswa.Loading
            try {
                // Memanggil fungsi dari repository untuk mengambil data dari Firestore
                val listSiswa = repositorySiswa.getDataSiswa()

                // Jika berhasil, update status menjadi Success dengan data list
                statusUiSiswa = StatusUiSiswa.Success(listSiswa)
            } catch (e: IOException) {
                // Kesalahan jaringan
                statusUiSiswa = StatusUiSiswa.Error
            } catch (e: Exception) {
                // Kesalahan umum lainnya
                statusUiSiswa = StatusUiSiswa.Error
            }
        }
    }
}