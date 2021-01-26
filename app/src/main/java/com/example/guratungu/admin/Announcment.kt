package com.example.guratungu.admin

import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.guratungu.R
import com.example.guratungu.admin.model.Catatan
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.Logger
import kotlinx.android.synthetic.main.layout_announcment.*
import kotlinx.android.synthetic.main.layout_tambahevent.*

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

    private fun simpan() {

        if (et_annocom.text.toString().isEmpty()) {
            et_annocom.error = "Masukkan Catatan Terlebih dahulu"
            et_annocom.requestFocus()
            return
        }
        val userId = ref.push().key.toString()
        val catatan = et_annocom.text.toString().trim()
        val catat = Catatan(userId,catatan)



        ref.child(userId).setValue(catat).addOnCompleteListener {
            Toast.makeText(this, "Sukses Tambah Catatan",Toast.LENGTH_LONG).show()
            et_annocom.setText("")
        }

    }

    private fun fullScreen() {
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
    }
}