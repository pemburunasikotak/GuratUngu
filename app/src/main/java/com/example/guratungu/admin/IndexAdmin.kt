package com.example.guratungu.admin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import com.example.guratungu.MainActivity
import com.example.guratungu.R
import com.example.guratungu.karyawan.LoginKaryawan
import com.google.firebase.FirebaseApp
import kotlinx.android.synthetic.main.layout_indexadmin.*

@Suppress("DEPRECATION")
class IndexAdmin : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_indexadmin)
        btn_creatakunindexadmin.setOnClickListener {
            val intent = Intent(this, RegisterAdmin::class.java)
            startActivity(intent)
        }
        btn_announcamentadmin.setOnClickListener {
            val intent = Intent(this, RegisterAdmin::class.java)
            startActivity(intent)
        }
        btn_keluarAdmin.setOnClickListener {
            keluar()
        }
        btn_logOutAdmin.setOnClickListener {
            keluar()
        }
        fullScreen()

    }

    private fun keluar() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        onDestroy()
    }


    private fun fullScreen() {
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
    }
}