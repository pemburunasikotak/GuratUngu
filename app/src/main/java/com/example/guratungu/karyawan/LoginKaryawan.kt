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
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import com.google.gson.Gson
import kotlinx.android.synthetic.main.layout_login_karyawan.*


@Suppress("DEPRECATION")
class LoginKaryawan : AppCompatActivity() {
    lateinit var ref : DatabaseReference
    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_login_karyawan)
        auth = FirebaseAuth.getInstance()
        ref = FirebaseDatabase.getInstance().getReference("karyawan")
        btn_singinloginkaryawan.setOnClickListener {
           // login()
            loginbaru()
        }
//        btn_creatakunloginkaryawan.setOnClickListener {
//            val intent = Intent(this, RegisterAdmin::class.java)
//            startActivity(intent)
//        }
        fullScreen()
    }

    private fun loginbaru() {
        if (it_emaillogin.text.toString().isEmpty()){
            it_emaillogin.error = "masukkan Email"
            it_emaillogin.requestFocus()
        }

        if (it_passwdlogin.text.toString().isEmpty()){
            it_passwdlogin.error ="masukkan Paswd yang benar"
            it_passwdlogin.requestFocus()
            return
        }
        auth.signInWithEmailAndPassword(
            it_emaillogin.text.toString(),
            it_passwdlogin.text.toString()
        )
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Log.d("Test", "signInWithEmail:success")
                    val user: FirebaseUser = auth.getCurrentUser()!!
                    updateUI(user)
                } else {
                    updateUI(null)
                }

            }
    }

    //fungsi untuk Verifikasi Email
    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        updateUI(currentUser)
    }
    fun getUser(email: String){
        var user: Users? = null
        ref.orderByChild("email").equalTo(email).addListenerForSingleValueEvent(object :
            ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
            }

            override fun onDataChange(p0: DataSnapshot) {
                for (snap in p0.children) {
                    user = snap.getValue(Users::class.java)
                }

                //AlStatic.setSPString(this@Login, App.instance.USER_KEY, Gson().toJson(user))
            }
        })
    }

    private fun updateUI(currentUser: FirebaseUser?) {
        if (currentUser != null){
            if (currentUser.isEmailVerified){
                getUser(currentUser.email!!)
                startActivity(Intent(this, IndexKaryawan::class.java))
                finish()
            }else{
                Toast.makeText(
                    baseContext, "Email belum Di Verifikasi silahkan cek Email",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }else{
            Toast.makeText(
                baseContext, "Login failed.",
                Toast.LENGTH_SHORT
            ).show()
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
        val email =it_emaillogin.text.toString().trim()
        val pasword= it_passwdlogin.text.toString()


        var query = FirebaseDatabase.getInstance().getReference("karyawan").orderByChild("email").equalTo(
            email
        )
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
                            Toast.makeText(
                                this@LoginKaryawan,
                                "Password is wrong",
                                Toast.LENGTH_LONG
                            ).show()
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