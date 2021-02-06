package com.example.guratungu.admin.listagenda

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.guratungu.MainActivity
import com.example.guratungu.R
import com.example.guratungu.admin.listchatgroup.ListChatGrup
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.list_agendaadmin.view.*

class ListAgendaAdapter(private val context: Context, private val listagenda:List<ListAgendaModel>):RecyclerView.Adapter<ListAgendaAdapter.ListAgendaHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ListAgendaHolder {
        return ListAgendaHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.list_agendaadmin, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ListAgendaHolder, position: Int) {
        var list = listagenda[position]

        holder.tveventadmin.text = list.nama
        holder.tvlokasiadmin.text= list.tempat
        holder.tvjamadmin.text = list.tgl
        holder.tvhariadmin.text = list.durasi
        holder.btn_edt.setOnClickListener {
            edit()
        }
        holder.btn_hps.setOnClickListener {
            delete()
        }
        holder.btn_karyawanJoin.setOnClickListener {
            val nama = list.tempat
            val  i = Intent(context, ListChatGrup::class.java)
            context.startActivity(i)
        }

    }

    private fun edit() {
        Toast.makeText(context, "Edit User",Toast.LENGTH_LONG)
    }

    private fun delete() {
        Toast.makeText(context, "Delete",Toast.LENGTH_LONG)

    }

    inner class ListAgendaHolder(val view: View) : RecyclerView.ViewHolder(view) {

        var tveventadmin : TextView = view.findViewById(R.id.tveventadmin)
        var tvlokasiadmin: TextView = view.findViewById(R.id.tvlokasiadmin)
        var tvjamadmin: TextView = view.findViewById(R.id.tvjamadmin)
        var tvhariadmin: TextView = view.findViewById(R.id.tvhariadmin)
        var btn_edt: Button = view.findViewById(R.id.btn_editListAgenda)
        var btn_hps: Button = view.findViewById(R.id.btn_hapusListAgenda)
        var btn_karyawanJoin: Button = view.findViewById(R.id.btn_karjoinListAgenda)
    }
    override fun getItemCount(): Int = listagenda.size
}


