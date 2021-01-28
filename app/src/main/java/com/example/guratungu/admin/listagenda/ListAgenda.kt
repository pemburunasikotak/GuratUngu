package com.example.guratungu.admin.listagenda

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.guratungu.R
import kotlinx.android.synthetic.main.layout_listagendaadmin.*

@Suppress("DEPRECATION")
class ListAgenda : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_listagendaadmin)

        val listagenda = listOf(
            ListAgendaModel("Nikahan","Janti","12","Senin"),
            ListAgendaModel("Nikahan","Janti","12","Senin"),
            ListAgendaModel("Nikahan","Janti","12","Senin"),
            ListAgendaModel("Nikahan","Janti","12","Senin"),
            ListAgendaModel("Nikahan","Janti","12","Senin"),
            ListAgendaModel("Nikahan","Janti","12","Senin")
        )
        val listagendaadapter = ListAgendaAdapter(listagenda)
        rvlistagendaadmin.apply {
            layoutManager = LinearLayoutManager(this@ListAgenda)
            adapter= listagendaadapter
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