package com.example.guratungu.karyawan.listagendakaryawan

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.guratungu.R
import com.example.guratungu.admin.model.Users
import com.example.guratungu.karyawan.model.Join
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.layout_login_admin.*

class ListAgendaAdapter (private val context: Context,
                         private val listagenda:List<ListAgendaModel>)
    :RecyclerView.Adapter<ListAgendaAdapter.ListAgendaHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ListAgendaHolder {
        val view: View= LayoutInflater.from(parent.context).inflate(
            R.layout.list_agenda,
            parent,
            false
        )
        return ListAgendaHolder(view)
    }


    override fun onBindViewHolder(holder: ListAgendaHolder, position: Int) {
        var list = listagenda[position]
        var join = FirebaseDatabase.getInstance().getReference("Listjoin")
        var test = FirebaseDatabase.getInstance().getReference("users").orderByChild("nama")


        holder.tvevent.text = list.nama
        holder.tvlokasi.text = list.tempat
        holder.tvjam.text = list.waktu
        holder.tvhari.text = list.hari
        holder.tvharitgl.text = list.hari

        holder.btn_join.setOnClickListener {
            val userId = join.push().key.toString()
            val name = list.User.nama
            val Joinin = Join(userId,name)

            if (userId.isEmpty()){
                Toast.makeText(holder.itemView.context, "Silahkan cek user", Toast.LENGTH_SHORT).show()
            }else{
                //Belum
                //Cari Fungsi Baca Nama Login
                join = FirebaseDatabase.getInstance().getReference("Listjoin")
                join.child(userId).setValue(Joinin).addOnCompleteListener {
                    Toast.makeText(holder.itemView.context, "Successs", Toast.LENGTH_SHORT).show()
                }
            }
            Toast.makeText(holder.itemView.context, "Anda Join Ke Event" + listagenda[holder.adapterPosition].nama, Toast.LENGTH_SHORT).show()
        }
        holder.btn_lokasi.setOnClickListener {
            val nama = list.tempat
            val  i = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/maps/search/$nama"))
            context.startActivity(i)
        }
    }

    inner class ListAgendaHolder(val view: View) : RecyclerView.ViewHolder(view) {
        var tvevent : TextView = view.findViewById(R.id.tvevent)
        var tvlokasi:TextView = view.findViewById(R.id.tvlokasi)
        var tvjam:TextView= view.findViewById(R.id.tvjam)
        var tvhari: TextView = view.findViewById(R.id.tvhari)
        var tvharitgl: TextView = view.findViewById(R.id.tvharitgl)
        var btn_join:Button = view.findViewById(R.id.btn_join)
        var btn_lokasi:Button = view.findViewById(R.id.btn_lokasi)

    }
    override fun getItemCount(): Int = listagenda.size

}


