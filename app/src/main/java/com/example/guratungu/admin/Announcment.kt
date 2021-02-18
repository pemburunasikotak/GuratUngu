package com.example.guratungu.admin

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.guratungu.R
import com.example.guratungu.admin.listannoncment.ListAnnoncment
import com.example.guratungu.admin.listannoncment.ListAnnoncmentModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.layout_announcment.*

@Suppress("DEPRECATION")
class Announcment : AppCompatActivity() {
    lateinit var ref : DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ref = FirebaseDatabase.getInstance().getReference("Catatan")
        setContentView(R.layout.layout_announcment)
        btn_tambahAnno.setOnClickListener {
            simpan()
        }
        fullScreen()
    }

    //fungsi untuk simpan
    private fun simpan() {

        //cek validasi data
        if (et_annocom.text.toString().isEmpty()) {
            et_annocom.error = "Masukkan Catatan Terlebih dahulu"
            et_annocom.requestFocus()
            return
        }
        val userId = et_annocom.text.toString().trim()
        val catatan = et_annocom.text.toString().trim()
        val catat = ListAnnoncmentModel(catatan)
        if (catatan.isEmpty()){
            Toast.makeText(this, "Masukkan Annountcmt", Toast.LENGTH_SHORT).show()
        }else{
            ref.child(userId).setValue(catat).addOnCompleteListener {
                Toast.makeText(this, "Sukses Tambah Catatan",Toast.LENGTH_LONG).show()
                et_annocom.setText("")
                val intent = Intent(this, ListAnnoncment::class.java)
                startActivity(intent)
            }
        }


    }

    private fun fullScreen() {
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
    }
}