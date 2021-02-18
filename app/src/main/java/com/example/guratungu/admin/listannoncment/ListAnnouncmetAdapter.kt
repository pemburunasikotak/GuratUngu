package com.example.guratungu.admin.listannoncment

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.guratungu.R
import com.example.guratungu.admin.listjoinadmin.ListChatGrup
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.list_annoncment.view.*


class ListAnnouncmetAdapter(private  val context: Context, private val listagenda:List<ListAnnoncmentModel>):RecyclerView.Adapter<ListAnnouncmetAdapter.ListAgendaHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ListAgendaHolder {
        return ListAgendaHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.list_annoncment, parent, false)
        )
    }
    override fun getItemCount(): Int = listagenda.size

    override fun onBindViewHolder(holder: ListAgendaHolder, position: Int) {
        var list = listagenda[position]
        holder.tv_disktipsian.text= list.catatan
        holder.buttonhapus.setOnClickListener {
            val ref = FirebaseDatabase.getInstance().getReference("Catatan").child(list.catatan)
            Log.d("test",ref.toString())
            val Query: Query = ref
            Query.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    for (appleSnapshot in dataSnapshot.children) {
                        appleSnapshot.ref.removeValue()
                    }
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    Log.e(ContentValues.TAG, "onCancelled", databaseError.toException())
                }
            })
            val intent = Intent(context, ListAnnoncment::class.java)
            context.startActivity(intent)
        }
    }

    inner class ListAgendaHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val tv_disktipsian :TextView = view.findViewById(R.id.tv_diskripsiann)
        val buttonhapus:Button = view.findViewById(R.id.btn_hapusListAnnon)




    }
}
