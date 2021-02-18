package com.example.guratungu.admin.listjoinadmin

   import android.content.Intent
import android.os.Bundle
   import android.util.Log
   import android.view.WindowManager
   import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.guratungu.R
   import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.layout_listachatgrupadmin.*

@Suppress("DEPRECATION")
class ListChatGrup : AppCompatActivity() {
    private var list : MutableList<ListChatModel> = ArrayList()
    private lateinit var rvData: RecyclerView
    private lateinit var namaevent : String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_listachatgrupadmin)
        rvData = findViewById(R.id.rvlistkaryawanjoin)
        rvData.setHasFixedSize(true)
        //ganti nama Event
        val bundle = intent.extras
        nama_event.setText(bundle?.getString("tveventadmin")).toString()
        namaevent = nama_event.text.toString()
        Log.d("namaEvent", namaevent)
        RecyclerCardView()
        fullScreen()
        btn_OkListChatGrup.setOnClickListener {
            val intent = Intent(this,com.example.guratungu.admin.listagenda.ListAgenda::class.java)
            startActivity(intent)
        }
    }
    private fun RecyclerCardView() {
        namaevent = nama_event.text.toString()
        val listadapter = ListChatGrupAdapter(this,list,namaevent)
        rvData.adapter =listadapter
        rvData.layoutManager=LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false)
       // val nama = nama_event.toString()
        var myRef = FirebaseDatabase.getInstance().getReference("Listjoin").child(namaevent)

        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (snap in snapshot.children){
                    val x = snap.getValue(ListChatModel::class.java)
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

