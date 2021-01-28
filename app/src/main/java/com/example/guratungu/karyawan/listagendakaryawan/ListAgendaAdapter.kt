package com.example.guratungu.karyawan.listagendakaryawan

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.guratungu.R
import kotlinx.android.synthetic.main.list_agenda.view.*

class ListAgendaAdapter(private val listagenda:List<ListAgendaModel>):RecyclerView.Adapter<ListAgendaAdapter.ListAgendaHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ListAgendaHolder {
        return ListAgendaHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.list_agenda, parent, false)
        )
    }
    override fun getItemCount(): Int = listagenda.size

    override fun onBindViewHolder(holder: ListAgendaHolder, position: Int) {

        holder.bindListChat(listagenda[position])
    }

    inner class ListAgendaHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bindListChat(listagenda: ListAgendaModel) {
            itemView.tvevent.text= listagenda.event
            itemView.tvlokasi.text= listagenda.lokasi
            itemView.tvjam.text =listagenda.jam.toString()
            itemView.tvhari.text = listagenda.hari
            itemView.btn_join.setOnClickListener {
                //onKonfirmasiPesan.onKonfirmasiPesan(listagenda)
            }
            itemView.btn_lokasi.setOnClickListener {
                //setMapLokasi
            }
        }
    }
    interface OnKonfirmasiPesan{
        fun onKonfirmasiPesan(listagenda: ListAgendaModel)
    }
}


