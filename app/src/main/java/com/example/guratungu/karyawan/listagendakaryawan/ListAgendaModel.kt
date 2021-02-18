package com.example.guratungu.karyawan.listagendakaryawan


import com.example.guratungu.admin.model.Users
import kotlinx.android.parcel.Parcelize
import com.google.firebase.database.PropertyName

//data class ListAgendaModel

data class ListAgendaModel(
    @PropertyName(value ="event")
    var nama:String ="",
    @PropertyName(value ="lokasi")
    var tempat: String="",
    @PropertyName(value ="jam")
    var waktu: String ="",
    @PropertyName(value ="tgl")
    var tgl:String="",
    @PropertyName(value ="hari")
    var hari:String=""
)