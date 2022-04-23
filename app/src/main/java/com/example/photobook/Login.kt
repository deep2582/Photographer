package com.example.photographer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.photographer.fragments.ProfileFragment
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val database = Firebase.database

        var mob = findViewById<EditText>(R.id.Mobile)
        var pass = findViewById<EditText>(R.id.Password)
        var login_btn = findViewById<Button>(R.id.cirLoginButton)
        var forgot = findViewById<TextView>(R.id.fp)

        login_btn.setOnClickListener{
            val m = mob.text.toString()
            val p = pass.text.toString()

            val fragment = ProfileFragment()
            val bundle = Bundle()
            bundle.putString("string", m)
            fragment.arguments = bundle

            if(mob!=null && pass!=null){
                var myRef = database.getReference("Users")
                myRef.child(m).get().addOnSuccessListener {
                    if(it.child("mobile").value == m && it.child("password").value == p){
                        startActivity(Intent(this, Home::class.java))
                        overridePendingTransition(R.anim.slide_in_right, android.R.anim.slide_out_right)
                    }
                    else{
                        Toast.makeText(this,"Username or Password incorrect!!", Toast.LENGTH_SHORT).show()
                    }
                }.addOnFailureListener{

                    Toast.makeText(this,"Failed",Toast.LENGTH_SHORT).show()


                }
            }
            else{
                Toast.makeText(this,"Enter all information correctly!!",Toast.LENGTH_SHORT).show()
            }
        }

        forgot.setOnClickListener{
            startActivity(Intent(this, ForgotPassword::class.java))
            overridePendingTransition(R.anim.slide_in_right, android.R.anim.slide_out_right)
        }
    }

    fun onLoginClick(view: View?) {
        startActivity(Intent(this, Register::class.java))
        overridePendingTransition(R.anim.slide_in_right, android.R.anim.slide_out_right)
    }
}