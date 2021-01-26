package com.example.guratungu.admin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import com.example.guratungu.R
import kotlinx.android.synthetic.main.layout_awal.*

@Suppress("DEPRECATION")
class LoginAdmin : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_login_admin)
        btn_awalkaryawan.setOnClickListener {
            //val intent = Intent(this, )
        }
        btn_awaladmin.setOnClickListener {

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