package com.example.guratungu.admin.listagenda

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.guratungu.R
import kotlinx.android.synthetic.main.list_agendaadmin.view.*

class ListAgendaAdapter(private val listagenda:List<ListAgendaModel>):RecyclerView.Adapter<ListAgendaAdapter.ListAgendaHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ListAgendaHolder {
        return ListAgendaHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.list_agendaadmin, parent, false)
        )
    }
    override fun getItemCount(): Int = listagenda.size

    override fun onBindViewHolder(holder: ListAgendaHolder, position: Int) {
        holder.bindListChat(listagenda[position])
    }

    inner class ListAgendaHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bindListChat(listagenda: ListAgendaModel) {
            itemView.apply {
                tveventadmin.text = listagenda.event
                tvlokasiadmin.text = listagenda.lokasi
                tvjamadmin.text = listagenda.jam
                tvhariadmin.text = listagenda.hari
            }
        }
    }
}


