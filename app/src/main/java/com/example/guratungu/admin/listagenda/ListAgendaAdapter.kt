package com.example.guratungu.admin.listagenda

import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.guratungu.R
import com.example.guratungu.admin.IndexAdmin
import com.example.guratungu.admin.TambahEvent
import com.example.guratungu.admin.listjoinadmin.ListChatGrup
import com.example.guratungu.admin.listjoinadmin.ListChatGrupAdapter
import com.google.firebase.database.*


class ListAgendaAdapter(private val context: Context, private val listagenda: List<ListAgendaModel>):RecyclerView.Adapter<ListAgendaAdapter.ListAgendaHolder>() {

    //private var database: DatabaseReference? = null
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ListAgendaHolder {
        return ListAgendaHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.list_agendaadmin, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ListAgendaHolder, position: Int) {
        //val database = FirebaseDatabase.getInstance().getReference("ListAgenda");
        var list = listagenda[position]

        holder.id.text= list.nama

        holder.tveventadmin.text = list.nama
        holder.tvlokasiadmin.text= list.tempat
        holder.tvjamadmin.text = list.tgl
        holder.tvhariadmin.text = list.hari
        holder.tvtgladmin.text= list.waktu


        holder.btn_edt.setOnClickListener {
            Toast.makeText(context, "Edit User", Toast.LENGTH_LONG)
            val bundel = Bundle()
            bundel.putString("tveventadmin", list.nama.toString())
            bundel.putString("tvlokasiadmin", list.tempat.toString())
            bundel.putString("tvjamadmin", list.tgl.toString())
            bundel.putString("tvhariadmin", list.hari.toString())
            bundel.putString("tvtgladmin", list.waktu.toString())
            val intent = Intent(context, TambahEvent::class.java)
            intent.putExtras(bundel)
            context.startActivity(intent)
        }

        holder.btn_hps.setOnClickListener {

            //val uid = list.id
            val ref = FirebaseDatabase.getInstance().getReference("ListAgenda").child(list.nama)
            val Query: Query = ref
            Query.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    for (appleSnapshot in dataSnapshot.children) {
                        appleSnapshot.ref.removeValue()
                    }
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    Log.e(TAG, "onCancelled", databaseError.toException())
                }
            })
            val intent = Intent(context, IndexAdmin::class.java)
            context.startActivity(intent)
        }
        holder.btn_karyawanJoin.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("tveventadmin", list.nama.toString())

            //editing
            val  intent = Intent(context, ListChatGrup::class.java)
            intent.putExtras(bundle)
            context.startActivity(intent)
        }

    }



    inner class ListAgendaHolder(val view: View) : RecyclerView.ViewHolder(view) {

        var id : TextView = view.findViewById(R.id.tvid)
        var tveventadmin : TextView = view.findViewById(R.id.tveventadmin)
        var tvlokasiadmin: TextView = view.findViewById(R.id.tvlokasiadmin)
        var tvjamadmin: TextView = view.findViewById(R.id.tvjamadmin)
        var tvhariadmin: TextView = view.findViewById(R.id.tvhariadmin)
        var tvtgladmin: TextView = view.findViewById(R.id.tvtgladmin)
        var btn_edt: Button = view.findViewById(R.id.btn_editListAgenda)
        var btn_hps: Button = view.findViewById(R.id.btn_hapusListAgenda)
        var btn_karyawanJoin: Button = view.findViewById(R.id.btn_karjoinListAgenda)
    }
    override fun getItemCount(): Int = listagenda.size
}


