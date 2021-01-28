package com.example.guratungu.admin.listchatgroup

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.guratungu.MainActivity
import com.example.guratungu.R
import kotlinx.android.synthetic.main.layout_listachatgrupadmin.*

@Suppress("DEPRECATION")
class ListChatGrup : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_listachatgrupadmin)

        val listchat= listOf(
            ListChatModel("jarno"),
            ListChatModel("tejo")
        )
        val listadapter = ListChatGrupAdapter(listchat)
        rvlistkaryawanjoin.apply {
            layoutManager = LinearLayoutManager(this@ListChatGrup)
            adapter= listadapter
        }

        btn_OkListChatGrup.setOnClickListener {
            val intent=Intent(this,MainActivity::class.java)
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

