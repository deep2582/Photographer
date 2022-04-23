package com.example.photographer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class ForgotPassword : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        var mob = findViewById<EditText>(R.id.fp_mobile)
        var pass = findViewById<EditText>(R.id.fp_np)
        var cpass = findViewById<EditText>(R.id.fp_cp)
        var reset_btn = findViewById<Button>(R.id.btn_reset)

        val database = Firebase.database

        reset_btn.setOnClickListener{
            var m = mob.text.toString()
            var p = pass.text.toString()
            var c = cpass.text.toString()
            if(mob!=null && pass!=null && cpass!=null){
                if(p==c){
                    var myRef = database.getReference("Users")
                    var data = myRef.child(m)
                    data.child("password").setValue(p).addOnSuccessListener {

                        mob.text.clear()
                        pass.text.clear()
                        cpass.text.clear()

                        Toast.makeText(this,"Password Changed successfully!",Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this, Login::class.java))
                        overridePendingTransition(R.anim.slide_in_left, android.R.anim.slide_out_right)

                    }.addOnFailureListener{

                        Toast.makeText(this,"Failed",Toast.LENGTH_SHORT).show()


                    }
                }
                else{
                    Toast.makeText(this,"Password doesn't match!!",Toast.LENGTH_SHORT).show()
                }
            }
            else{
                Toast.makeText(this,"Enter all information correctly!!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}