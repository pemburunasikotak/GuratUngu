package com.example.guratungu.karyawan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.Toast
import com.example.guratungu.R
import com.example.guratungu.admin.LoginAdmin
import com.example.guratungu.admin.RegisterAdmin
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.layout_awal.*
import kotlinx.android.synthetic.main.layout_login_karyawan.*

@Suppress("DEPRECATION")
class LoginKaryawan : AppCompatActivity() {
    lateinit var ref : DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_login_karyawan)

        fullScreen()
        btn_singinloginkaryawan.setOnClickListener {
            login()
        }
        btn_creatakunloginkaryawan.setOnClickListener {
            val intent = Intent(this, RegisterAdmin::class.java)
            startActivity(intent)
        }

    }


    private fun login() {
        if (it_emaillogin.text.toString().isEmpty()){
            it_emaillogin.error = "masukkan Email"
            it_emaillogin.requestFocus()
        }

        if (it_passwdlogin.text.toString().isEmpty()){
            it_passwdlogin.error ="masukkan Paswd yang benar"
            it_passwdlogin.requestFocus()
            return
        }
        val email =it_emaillogin.text.toString()
        val pasword= it_passwdlogin.text.toString()
        val emailfirebase = ref.child("email").toString()
        val passwdfirebase= ref.child("password")
        if (email.equals(emailfirebase)&&pasword.equals(passwdfirebase)){
            val intent =Intent(this,Index::class.java)
            startActivity(intent)
        }else{
            Toast.makeText(
                baseContext,
                "Login Gagal data tidak di temukan ",
                Toast.LENGTH_SHORT
            )
        }

    }
    private fun fullScreen() {
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
    }
}