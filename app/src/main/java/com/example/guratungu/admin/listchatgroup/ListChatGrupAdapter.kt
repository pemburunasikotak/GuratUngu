package com.example.guratungu.admin.listchatgroup

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.guratungu.R
import kotlinx.android.synthetic.main.list_karyawanjoin.view.*

class ListChatGrupAdapter(private val listchet:List<ListChatModel>):RecyclerView.Adapter<ListChatGrupAdapter.ListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ListViewHolder {
        return ListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.list_karyawanjoin, parent, false)
        )
    }

    override fun getItemCount(): Int = listchet.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bindListChat(listchet[position])
    }

    inner class ListViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bindListChat(listchat: ListChatModel) {
            itemView.apply {
                tv_namaList.text = listchat.nama
            }
        }
    }
}


