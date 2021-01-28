package com.example.guratungu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import com.example.guratungu.admin.LoginAdmin
import com.example.guratungu.karyawan.LoginKaryawan
import kotlinx.android.synthetic.main.layout_awal.*

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_awal)
        btn_awalkaryawan.setOnClickListener {
            val intent = Intent(this, LoginKaryawan::class.java)
            startActivity(intent)
        }
        btn_awaladmin.setOnClickListener {
            val intent = Intent(this, LoginAdmin::class.java)
            startActivity(intent)
        }
        fullScreen()
    }

    private fun fullScreen() {
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
    }
}