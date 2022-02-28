package com.example.photobook

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class Register : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)



        val RegisterBtn=findViewById<Button>(R.id.cirRegisterButton)
        val etName=findViewById<EditText>(R.id.etName)
        val etMobile=findViewById<EditText>(R.id.etMobile)
        val etEmail=findViewById<EditText>(R.id.etEmail)
        val etPassword=findViewById<EditText>(R.id.etPassword)
        val etCPassword=findViewById<EditText>(R.id.etCPassword)
        val role=findViewById<Spinner>(R.id.Role)


        RegisterBtn.setOnClickListener {


            val database = Firebase.database
            var Name = etName.text.toString()
            val Email = etEmail.text.toString()
            val Mobile = etMobile.text.toString()
            val Password = etPassword.text.toString()
            val CPassword = etCPassword.text.toString()
            val Role = role.getSelectedItem().toString()



            if(Name!=null && Email!=null && Mobile!=null && Password!=null && CPassword!=null){
                if(Password == CPassword){
                    val User = User(Name,Email,Mobile,Password,Role)
                    var myRef = database.getReference("Users")
                    var data = myRef.child(Email)
                    data.setValue(User).addOnSuccessListener {

                        etName.text.clear()
                        etEmail.text.clear()
                        etMobile.text.clear()
                        etPassword.text.clear()
                        etCPassword.text.clear()

                        Toast.makeText(this,"Account created successfully!",Toast.LENGTH_SHORT).show()
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
                Toast.makeText(this,"Enter all information correctly!!",Toast.LENGTH_SHORT).show()
            }




        }


        //Code for spinner
        val spinner: Spinner = findViewById(R.id.Role)

        ArrayAdapter.createFromResource(
            this,
            R.array.role,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }
    }
    fun onLoginClick(view: View?) {
        startActivity(Intent(this, Login::class.java))
        overridePendingTransition(R.anim.slide_in_left, android.R.anim.slide_out_right)
    }

}