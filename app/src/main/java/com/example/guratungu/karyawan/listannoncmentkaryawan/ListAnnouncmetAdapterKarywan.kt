package com.example.guratungu.karyawan.listannoncmentkaryawan

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.guratungu.R
import kotlinx.android.synthetic.main.list_annoncmentkaryawan.view.*


class ListAnnouncmetAdapterKarywan(private val listagenda:List<ListAnnoncmentModelKarywan>):RecyclerView.Adapter<ListAnnouncmetAdapterKarywan.ListAgendaHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ListAgendaHolder {
        return ListAgendaHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.list_annoncmentkaryawan, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ListAgendaHolder, position: Int) {
        holder.bindListChat(listagenda[position])
    }
    override fun getItemCount(): Int = listagenda.size


    inner class ListAgendaHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bindListChat(listagenda: ListAnnoncmentModelKarywan) {
            itemView.tv_diskripsiannkarywan.text = listagenda.catatan     
        }
    }


}


