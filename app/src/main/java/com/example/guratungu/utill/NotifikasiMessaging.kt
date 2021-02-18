package com.example.guratungu.utill

import android.util.Log
import com.example.guratungu.karyawan.model.Notif
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.google.gson.Gson


//Fungsi dari Firebase Cloud Massagin
//digunakan untuk notifikasi
//buka di Firebase
class NotifikasiMessaging : FirebaseMessagingService() {
    override fun onCreate() {
        super.onCreate()
    }
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        //Log.d("ola",  "onMessageReceived data : " + remoteMessage.getData())

        val notification = Gson().fromJson(remoteMessage.getData().get("data").toString(), Notif::class.java)
        notification.title = remoteMessage.getData().get("title").toString()
        notification.description = remoteMessage.getData().get("description").toString()
        //Log.d("ola",  "onMessageReceived data : " + notification.title+" = "+notification.description+" = "+notification.estimasi)
        if(!notification.id.equals(FirebaseAuth.getInstance().currentUser!!.uid)){
            Notification(applicationContext, notification)
        }
    }
}