package com.example.guratungu.karyawan.listagendakaryawan

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.guratungu.R
import com.example.guratungu.admin.model.Karywan
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.layout_listagenda.*

@Suppress("DEPRECATION")
class ListAgenda : AppCompatActivity() {

    private var list : MutableList<ListAgendaModel> = ArrayList()
    private lateinit var rvData: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_listagenda)
        rvData = findViewById(R.id.rvlistagenda)
        rvData.setHasFixedSize(true)
        RecyclerCardView()
        fullScreen()
        gantinama()
    }

    private fun gantinama() {
            val dataRef = FirebaseDatabase.getInstance().getReference("karyawan")
            var user: Karywan? = null
            //var pengguna = FirebaseAuth.getInstance().currentUser
            //var test = pengguna?.uid.toString()

            val currentuser = FirebaseAuth.getInstance().currentUser?.email.toString()
            dataRef.orderByChild("email").equalTo(currentuser)
                .addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onCancelled(p0: DatabaseError) {
                        //Log.d("apaini", user?.nama!!)
                    }

                    override fun onDataChange(p0: DataSnapshot) {
                        for (snap in p0.children) {
                            user = snap.getValue(Karywan::class.java)
                        }
                        //Log.d("apaini", user?.nama!!)
                        // tvalamat_profil_pemilik_bawah.text = user!!.nama
                        // tvnotelfon_profil_pemilik_bawah.text = user!!.no_telp
                        tv_namaLogin.setText(user!!.nama)
                    }
                })
    }

    private fun RecyclerCardView() {
        val listadapter = ListAgendaAdapter(this,list)
        rvData.adapter =listadapter
        rvData.layoutManager=LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false)
        var myRef = FirebaseDatabase.getInstance().getReference("ListAgenda")
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (snap in snapshot.children){
                    val x = snap.getValue(ListAgendaModel::class.java)
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

