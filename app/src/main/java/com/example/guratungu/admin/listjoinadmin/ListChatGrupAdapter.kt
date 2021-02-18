package com.example.guratungu.admin.listjoinadmin

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
import com.example.guratungu.admin.listagenda.ListAgenda
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.layout_listachatgrupadmin.*

class ListChatGrupAdapter(private val context: Context, private val listchet:List<ListChatModel>, private val namaevent: String):RecyclerView.Adapter<ListChatGrupAdapter.ListViewHolder>() {
  //  private lateinit var namaevent : String
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ListViewHolder {

        return ListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.list_karyawanjoin, parent, false)
        )
    }

    override fun getItemCount(): Int = listchet.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        var list = listchet[position]
        holder.tv_namaList.text= list.nama

        holder.btn_hps.setOnClickListener {
            //val uid = list.id
            val ref = FirebaseDatabase.getInstance().getReference("Listjoin").child(namaevent).child(list.nama)

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
            val intent = Intent(context, ListChatGrup::class.java)
            context.startActivity(intent)
        }
    }

    inner class ListViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        var  tv_namaList : TextView = view.findViewById(R.id. tv_namaList)
        var btn_hps: Button = view.findViewById(R.id.btn_hapuslistdaftar)
    }
}


