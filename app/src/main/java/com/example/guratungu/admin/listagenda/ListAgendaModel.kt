package com.example.guratungu.admin.listagenda

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ListAgendaModel(
    var event:String ="",
    var lokasi: String="",
    var jam:String="",
    var hari:String=""
):Parcelable