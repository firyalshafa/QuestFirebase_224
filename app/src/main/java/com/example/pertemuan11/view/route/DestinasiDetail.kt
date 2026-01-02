package com.example.pertemuan11.view.route

import com.example.pertemuan11.R
import com.example.pertemuan11.view.route.DestinasiNavigasi

object DestinasiDetail: DestinasiNavigasi {
    override val route = "detail_siswa"
    override val titleRes = R.string.detail_siswa
    const val itemIdArg = "idSiswa"
    val routeWithArgs = "$route/{$itemIdArg}"
}