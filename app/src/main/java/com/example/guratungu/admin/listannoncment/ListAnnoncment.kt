package com.example.guratungu.admin.listannoncment

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.guratungu.R
import com.example.guratungu.admin.Announcment
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.layout_listannoincmentadmin.*

@Suppress("DEPRECATION")
class ListAnnoncment : AppCompatActivity() {

    private var list : MutableList<ListAnnoncmentModel> = ArrayList()
    private lateinit var rvData: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_listannoincmentadmin)
        btn_tambah.setOnClickListener {
            val intent = Intent(this, Announcment::class.java)
            startActivity(intent)
        }
        rvData = findViewById(R.id.rvlistannouncment)
        rvData.setHasFixedSize(true)
        RecyclerCardView()

        fullScreen()
    }

    private fun RecyclerCardView() {
        val listadapter = ListAnnouncmetAdapter(this,list)
        rvData.adapter =listadapter
        rvData.layoutManager=LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false)

        var myRef = FirebaseDatabase.getInstance().getReference("Catatan")

        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (snap in snapshot.children){
                    val x = snap.getValue(ListAnnoncmentModel::class.java)
                    //Log.e("testsoal", Gson().toJson(x))
                    list.add(x!!)
                    listadapter.notifyDataSetChanged()
                }
            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }

    private fun fullScreen() {
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
    }
}