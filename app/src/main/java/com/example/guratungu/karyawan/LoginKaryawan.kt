package com.example.guratungu.karyawan

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.guratungu.R
import com.example.guratungu.admin.model.Karywan
import com.example.guratungu.admin.model.Users
import com.google.firebase.database.*
import com.google.gson.Gson
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
//        btn_creatakunloginkaryawan.setOnClickListener {
//            val intent = Intent(this, RegisterAdmin::class.java)
//            startActivity(intent)
//        }

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
        val email =it_emaillogin.text.toString().trim()
        val pasword= it_passwdlogin.text.toString()


        var query = FirebaseDatabase.getInstance().getReference("karyawan").orderByChild("email").equalTo(email)
        query.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (snap in snapshot.children) {
                        val x = snap.getValue(Karywan::class.java)
                        Log.e("testsoal", Gson().toJson(x))
                        if (x!!.password.equals(pasword.trim())) {
                            val intent = Intent(this@LoginKaryawan, IndexKaryawan::class.java)
                            startActivity(intent)
                        } else {
                            Toast.makeText(this@LoginKaryawan, "Password is wrong", Toast.LENGTH_LONG).show()
                        }
                    }
                }

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })


//        val user = ref.child("User")
//        val emailfirebase = ref.child("email").toString()
//        val passwdfirebase= ref.child("password")
//        if (email.equals(emailfirebase)&&pasword.equals(passwdfirebase)){
//            val intent =Intent(this,Index::class.java)
//            startActivity(intent)
//        }else{
//            Toast.makeText(
//                baseContext,
//                "Login Gagal data tidak di temukan ",
//                Toast.LENGTH_SHORT
//            )
//        }

    }
    private fun fullScreen() {
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
    }
}