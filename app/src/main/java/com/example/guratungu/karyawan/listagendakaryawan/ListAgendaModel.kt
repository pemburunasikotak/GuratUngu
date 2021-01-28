package com.example.guratungu.karyawan.listagendakaryawan

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import com.google.firebase.database.PropertyName

//data class ListAgendaModel
@Parcelize
data class ListAgendaModel(
    @PropertyName(value ="event")
    var event:String ="",
    @PropertyName(value ="lokasi")
    var lokasi: String="",
    @PropertyName(value ="jam")
    var jam: Int =0,
    @PropertyName(value ="hari")
    var hari:String=""
) : Parcelable