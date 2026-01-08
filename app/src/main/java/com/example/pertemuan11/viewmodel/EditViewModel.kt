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

}