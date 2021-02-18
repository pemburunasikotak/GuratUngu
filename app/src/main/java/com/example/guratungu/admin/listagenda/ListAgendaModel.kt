package com.example.guratungu.admin.listagenda

import android.os.Parcelable
import com.google.firebase.database.PropertyName
import kotlinx.android.parcel.Parcelize

open class ListAgendaModel(
    var id : String =" ",
    @PropertyName(value ="event")
    var nama:String ="",
    @PropertyName(value ="lokasi")
    var tempat: String="",
    @PropertyName(value ="jam")
    var waktu: String ="",
    @PropertyName(value ="tgl")
    var tgl:String="",
    @PropertyName(value ="hari")
    var hari:String="",


    )