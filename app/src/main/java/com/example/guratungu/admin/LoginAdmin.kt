package com.example.guratungu.admin

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.guratungu.R
import com.example.guratungu.admin.model.Users
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.gson.Gson
import kotlinx.android.synthetic.main.layout_login_admin.*

@Suppress("DEPRECATION")
class LoginAdmin : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_login_admin)
        btn_loginlayoutadmin.setOnClickListener {
            //val intent = Intent(this, )
            login()
        }


        fullScreen()

    }

    private fun login() {
        if (it_emailloginadmin.text.toString().isEmpty()){
            it_emailloginadmin.error = "masukkan Email"
            it_emailloginadmin.requestFocus()
        }

        if (it_passwdloginadmin.text.toString().isEmpty()){
            it_passwdloginadmin.error ="masukkan Paswd yang benar"
            it_passwdloginadmin.requestFocus()
            return
        }
        val user =it_emailloginadmin.text.toString().trim()
        val pasword= it_passwdloginadmin.text.toString()


        var query = FirebaseDatabase.getInstance().getReference("admin").orderByChild("user").equalTo(user)
        query.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (snap in snapshot.children) {
                        val x = snap.getValue(Users::class.java)
                        Log.e("test", Gson().toJson(x))
                        if (x!!.password.equals(pasword.trim())) {
                            val intent = Intent(this@LoginAdmin, IndexAdmin::class.java)
                            startActivity(intent)
                        } else {
                            Toast.makeText(this@LoginAdmin, "User Tidak Ditemukan", Toast.LENGTH_LONG).show()
                        }
                    }
                }

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }


        private fun fullScreen() {
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
    }
}