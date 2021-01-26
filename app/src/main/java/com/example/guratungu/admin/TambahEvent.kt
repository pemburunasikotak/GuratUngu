package com.example.guratungu.admin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.WindowManager
import android.widget.Toast
import com.example.guratungu.R
import com.example.guratungu.admin.LoginAdmin
import com.example.guratungu.admin.model.Event
import com.example.guratungu.admin.model.Users
import com.example.guratungu.karyawan.LoginKaryawan
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.layout_awal.*
import kotlinx.android.synthetic.main.layout_form_registrasi.*
import kotlinx.android.synthetic.main.layout_tambahevent.*

@Suppress("DEPRECATION")
class TambahEvent : AppCompatActivity() {
    lateinit var ref : DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ref = FirebaseDatabase.getInstance().getReference("Event")
        setContentView(R.layout.layout_tambahevent)
        btn_tambaevent.setOnClickListener {
            tambah()
        }
        fullScreen()
    }

    private fun tambah() {
        if (et_namatambahevent.text.toString().isEmpty()) {
            et_namatambahevent.error = "Masukkan Nama Event"
            et_namatambahevent.requestFocus()
        }
        if (et_wkttambahevent.text.toString().isEmpty()) {
            et_wkttambahevent.error = "Masukkan wkt"
            et_wkttambahevent.requestFocus()
        }

        if (et_tgltambahevent.text.toString().isEmpty()) {
            et_tgltambahevent.error = "Masukkan tgl"
            et_tgltambahevent.requestFocus()
        }
        if (et_tempattambahevent.toString().isEmpty()) {
            et_tempattambahevent.error = "Masukkan tempat"
            et_tempattambahevent.requestFocus()

        }
        if (et_lattambahevent.toString().isEmpty()) {
            et_lattambahevent.error = "Masukkan lat"
            et_lattambahevent.requestFocus()

        }
        if (et_longtambahevent.toString().isEmpty()) {
            et_longtambahevent.error = "Masukkan long"
            et_longtambahevent.requestFocus()
        }
        if (et_durasitambahevent.toString().isEmpty()) {
            et_durasitambahevent.error = "Masukkan durasi"
            et_durasitambahevent.requestFocus()
            return
        }
        val userId = ref.push().key.toString()
        val nama = et_namatambahevent.text.toString().trim()
        val waktu = et_wkttambahevent.text.toString().trim()
        val tgl = et_tgltambahevent.text.toString().trim()
        val tempat = et_tempattambahevent.text.toString().trim()
        val lat = et_lattambahevent.text.toString().trim()
        val long = et_longtambahevent.text.toString().trim()
        val durasi = et_durasitambahevent.text.toString().trim()
        val event = Event(userId, nama, waktu,tgl,tempat,lat,long, durasi)

        ref.child(userId).setValue(event).addOnCompleteListener {
            Toast.makeText(this, "Sukses Tambah data ",Toast.LENGTH_LONG).show()
            et_namatambahevent.setText("")
            et_wkttambahevent.setText("")
            et_tempattambahevent.setText("")
            et_lattambahevent.setText("")
            et_longtambahevent.setText("")
            et_durasitambahevent.setText("")
        }
    }

    private fun fullScreen() {
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
    }
}