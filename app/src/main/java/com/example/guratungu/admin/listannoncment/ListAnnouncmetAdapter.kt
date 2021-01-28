package com.example.guratungu.admin.listannoncment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.guratungu.R
import kotlinx.android.synthetic.main.list_annoncment.view.*


class ListAnnouncmetAdapter(private val listagenda:List<ListAnnoncmentModel>):RecyclerView.Adapter<ListAnnouncmetAdapter.ListAgendaHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ListAgendaHolder {
        return ListAgendaHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.list_annoncment, parent, false)
        )
    }
    override fun getItemCount(): Int = listagenda.size

    override fun onBindViewHolder(holder: ListAgendaHolder, position: Int) {
        holder.bindListChat(listagenda[position])
    }

    inner class ListAgendaHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bindListChat(listagenda: ListAnnoncmentModel) {
            itemView.apply {
                tv_diskripsiann.text = listagenda.catatan
            }
        }
    }
}


