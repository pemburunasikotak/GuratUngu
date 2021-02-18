package com.example.guratungu.admin

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.guratungu.R
import com.example.guratungu.admin.listagenda.ListAgenda
import com.example.guratungu.admin.model.Event
import com.example.guratungu.notifikasi.FirebaseService
import com.example.guratungu.notifikasi.NotificationData
import com.example.guratungu.notifikasi.PushNotification
import com.example.guratungu.notifikasi.RetrofitInstance
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.messaging.FirebaseMessaging
import com.google.gson.Gson
import kotlinx.android.synthetic.main.layout_tambahevent.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Suppress("DEPRECATION")
class TambahEvent : AppCompatActivity() {
    val TOPIC = "/topics/myTopic2"
    val TAG = "MainActivity"

    lateinit var ref : DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseService.sharedPref = getSharedPreferences("sharedPref", Context.MODE_PRIVATE)
        FirebaseInstanceId.getInstance().instanceId.addOnSuccessListener {
            FirebaseService.token = it.token
            //etToken.setText(it.token)
        }
        ref = FirebaseDatabase.getInstance().getReference("ListAgenda")
        setContentView(R.layout.layout_tambahevent)

        //FCM
        FirebaseMessaging.getInstance().subscribeToTopic(TOPIC)

        //PR BELUM KELAR
        //Intent Put Extra

        val bundle = intent.extras
        et_namatambahevent.setText(bundle?.getString("tveventadmin"))
        et_tempattambahevent.setText(bundle?.getString("tvlokasiadmin"))
        et_wkttambahevent.setText(bundle?.getString("tvjamadmin"))
        et_lattambahevent.setText(bundle?.getString("tvhariadmin"))
        et_tgltambahevent.setText(bundle?.getString("tvtgladmin"))
        //Ahir Inten Put Extra

        btn_tambaevent.setOnClickListener {
            tambah()

        }
        fullScreen()
    }
    private fun sendNotification(notification: PushNotification) = CoroutineScope(Dispatchers.IO).launch {
        try {
            val response = RetrofitInstance.api.postNotification(notification)
            if(response.isSuccessful) {
                Log.d(TAG, "Response: ${Gson().toJson(response)}")
            } else {
                Log.e(TAG, response.errorBody().toString())
            }
        } catch(e: Exception) {
            Log.e(TAG, e.toString())
        }
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

        if (et_durasitambahevent.toString().isEmpty()) {
            et_durasitambahevent.error = "Masukkan durasi"
            et_durasitambahevent.requestFocus()
            return
        }
        //Notifikasi

        val title = et_namatambahevent.text.toString()
        val message = et_namatambahevent.text.toString()
        //val recipientToken = etToken.text.toString()

        Log.d("testtingmasage", title)
        //&& recipientToken.isNotEmpty()
        Toast.makeText(this, message, Toast.LENGTH_LONG)
        //Toast.makeText(this, title, Toast.LENGTH_LONG)
        //Toast.makeText(this, TOPIC, Toast.LENGTH_LONG)
        if(title.isNotEmpty() && message.isNotEmpty() ) {
            PushNotification(
                NotificationData(title, message),
                TOPIC
                //recipientToken

            ).also {
                sendNotification(it)

                Log.d("testtingmasage", message)
            }
        }

        //ahir notifikasi

        val userId = ref.push().key.toString()
        val nama = et_namatambahevent.text.toString().trim()
        val waktu = et_wkttambahevent.text.toString().trim()
        val tgl = et_tgltambahevent.text.toString().trim()
        val tempat = et_tempattambahevent.text.toString().trim()
        val hari = et_lattambahevent.text.toString().trim()
        //val long = et_longtambahevent.text.toString().trim()
        val durasi = et_durasitambahevent.text.toString().trim()
        val event = Event(userId, nama, waktu,tgl,tempat,hari, durasi)
        ref.child(nama).setValue(event).addOnCompleteListener {
            Toast.makeText(this, "Sukses Tambah data ",Toast.LENGTH_LONG).show()
            val intent = Intent(this, ListAgenda::class.java)
            startActivity(intent)
        }
    }

    private fun fullScreen() {
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
    }
}