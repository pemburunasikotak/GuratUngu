package com.example.guratungu.admin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.WindowManager
import android.widget.Toast
import com.example.guratungu.R
import com.example.guratungu.admin.model.Karywan
import com.example.guratungu.admin.model.Users
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.layout_awal.*
import kotlinx.android.synthetic.main.layout_form_registrasi.*

@Suppress("DEPRECATION")
class RegisterAdmin : AppCompatActivity() {
    lateinit var ref : DatabaseReference
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_form_registrasi)
        auth = FirebaseAuth.getInstance()
        ref = FirebaseDatabase.getInstance().getReference("karyawan")
        btn_registrasi.setOnClickListener {
            registrasi()
        }
        fullScreen()

    }


    private fun savedatatorealtimedata(){
        val userId = ref.push().key.toString()
        val nama = et_namaregistrasi.text.toString().trim()
        val email = et_emailregistrasi.text.toString().trim()
        val password = et_paswdregistrasi.text.toString().trim()
        val no_telp = et_notelpregistrasi.text.toString().trim()
        val user = Karywan(userId,nama, email, password, no_telp)

        this.ref = FirebaseDatabase.getInstance().getReference("karyawan")
        ref.child(userId).setValue(user).addOnCompleteListener {
            Toast.makeText(this, "Successs", Toast.LENGTH_SHORT).show()
            Log.d("test", ref.toString())
            startActivity(Intent(this, IndexAdmin::class.java))
        }
    }

    private fun registrasi() {
        //Validasi untuk data
        if (et_namaregistrasi.text.toString().isEmpty()) {
            et_namaregistrasi.error = "Masukkan Nama"
            et_namaregistrasi.requestFocus()
        }
        if (et_emailregistrasi.text.toString().isEmpty()) {
            et_emailregistrasi.error = "Masukkan Email"
            et_emailregistrasi.requestFocus()
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(et_emailregistrasi.text.toString()).matches()) {
            et_emailregistrasi.error = "Masukkan Email Valid"
            et_emailregistrasi.requestFocus()
        }
        if (et_notelpregistrasi.text.toString().isEmpty()) {
            et_notelpregistrasi.error = "Masukkan No Telfon"
            et_notelpregistrasi.requestFocus()
        }
        if (et_paswdregistrasi.toString().isEmpty()) {
            et_paswdregistrasi.error = "Masukkan Paswd yang benar"
            et_paswdregistrasi.requestFocus()
            return
        }
      //val userId = ref.push().key.toString()
        val nama = et_namaregistrasi.text.toString().trim()
        val email = et_emailregistrasi.text.toString().trim()
        val password = et_paswdregistrasi.text.toString().trim()
        val no_telp = et_notelpregistrasi.text.toString().trim()
       // val user = Karywan(userId,nama, email, password, no_telp)

            auth.createUserWithEmailAndPassword(
                email, password
            ).addOnCompleteListener(this@RegisterAdmin) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    user?.sendEmailVerification()

                        ?.addOnCompleteListener { //task ->
                            Log.d("test",user.toString())
                            if (task.isSuccessful) {
                                Toast.makeText(
                                    baseContext,
                                    "Silahkan Cek Email",
                                    Toast.LENGTH_SHORT
                                ).show()
                                savedatatorealtimedata()
                            }
                        }
                } else {
                    Toast.makeText(
                        baseContext, "Ulang Lagi",
                        Toast.LENGTH_SHORT
                    ).show()

            }
            //Firebase database


        }
    }

    private fun fullScreen() {
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
    }
}