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
import com.example.guratungu.admin.model.Karywan
import com.example.guratungu.admin.model.Users
import com.example.guratungu.karyawan.IndexKaryawan
import com.example.guratungu.karyawan.LoginKaryawan
import com.example.guratungu.karyawan.model.Join
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.gson.Gson
import kotlinx.android.synthetic.main.layout_listagenda.*
import kotlinx.android.synthetic.main.layout_login_admin.*
import kotlinx.android.synthetic.main.layout_login_karyawan.*

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

        //var test = FirebaseDatabase.getInstance().getReference("users").orderByChild("nama")

        holder.tvevent.text = list.nama
        holder.tvlokasi.text = list.tempat
        holder.tvjam.text = list.waktu
        holder.tvhari.text = list.hari
        holder.tvharitgl.text = list.tgl

        //Intent

        holder.btn_join.setOnClickListener {

            val currentuser = FirebaseAuth.getInstance().currentUser?.email.toString()
            val dataRef = FirebaseDatabase.getInstance().getReference("karyawan")
            var user: Users?
            val userId = dataRef.push().key.toString()
            var joindata = FirebaseDatabase.getInstance().getReference("Listjoin")

            dataRef.orderByChild("email").equalTo(currentuser)
                .addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onCancelled(p0: DatabaseError) {
                        //Log.d("apaini", user?.nama!!)
                    }

                    override fun onDataChange(p0: DataSnapshot) {
                        for (snap in p0.children) {
                            user = snap.getValue(Users::class.java)
                            val namauser = user!!.nama
                            val Joinin = Join(userId, namauser)

                            joindata.child(list.nama).child(namauser).setValue(Joinin).addOnCompleteListener {
                                Toast.makeText(holder.itemView.context, "Successs", Toast.LENGTH_SHORT).show()
                            }
                        }
                        //Log.d("apaini", user?.nama!!)
                        // tvalamat_profil_pemilik_bawah.text = user!!.nama
                        // tvnotelfon_profil_pemilik_bawah.text = user!!.no_telp
                       // tv_nama_home_pemilik.text = user!!.nama
                    }
                })

            //test untuk data list
//            var currentuser1 = FirebaseAuth.getInstance().currentUser?.email.toString()
//            currentuser1=currentuser1.split("@gmail.com").toString()
//            var join = FirebaseDatabase.getInstance().getReference("Listjoin")
//            val userId = join.push().key.toString()
//            val Joinin = Join(userId, currentuser1)
//
//            if (userId.isEmpty()){
//                Toast.makeText(holder.itemView.context, "Silahkan cek user", Toast.LENGTH_SHORT).show()
//            }else{
//                join = FirebaseDatabase.getInstance().getReference("Listjoin")
//                join.child(list.nama).child(list.nama).setValue(Joinin).addOnCompleteListener {
//                    Toast.makeText(holder.itemView.context, "Successs", Toast.LENGTH_SHORT).show()
//                }
//            }
//            Toast.makeText(holder.itemView.context, "Anda Join Ke Event" + listagenda[holder.adapterPosition].nama, Toast.LENGTH_SHORT).show()
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


