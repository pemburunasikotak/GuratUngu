package com.example.guratungu.karyawan.model


import com.example.guratungu.admin.listagenda.ListAgendaModel

class Notif(var title: String = "", var description : String = "", var data : ListAgendaModel) :
    ListAgendaModel() {
}