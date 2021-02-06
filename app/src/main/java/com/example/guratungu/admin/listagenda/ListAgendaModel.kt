package com.example.guratungu.admin.listagenda

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ListAgendaModel(
    var nama:String ="",
    var tempat: String="",
    var durasi:String="",
    var tgl:String=""
):Parcelable