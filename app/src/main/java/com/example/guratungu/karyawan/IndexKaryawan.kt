package com.example.guratungu.karyawan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import com.example.guratungu.R
import com.example.guratungu.karyawan.listagendakaryawan.ListAgenda
import com.example.guratungu.karyawan.listannoncmentkaryawan.ListAnnoncmentkarywan
import kotlinx.android.synthetic.main.layout_indexkaryawan.*

@Suppress("DEPRECATION")
class IndexKaryawan : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_indexkaryawan)

        btn_infoevent.setOnClickListener {
            val intent = Intent(this, ListAgenda::class.java)
            startActivity(intent)
        }
        btn_announcament.setOnClickListener {
            val intent = Intent(this, ListAnnoncmentkarywan::class.java)
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