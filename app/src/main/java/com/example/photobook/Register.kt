package com.example.photographer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.core.content.ContextCompat.startActivity
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class Register : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)


        val RegisterBtn=findViewById<Button>(R.id.cirRegisterButton)
        val etFName=findViewById<EditText>(R.id.et_fn)
        val etLName=findViewById<EditText>(R.id.et_ln)
        val etMobile=findViewById<EditText>(R.id.et_m)
        val etEmail=findViewById<EditText>(R.id.Mobile)
        val etPassword=findViewById<EditText>(R.id.et_p)
        val etCPassword=findViewById<EditText>(R.id.et_cp)
        val etcity=findViewById<EditText>(R.id.et_city)
        val etstate=findViewById<EditText>(R.id.et_state)
        val etcountry=findViewById<EditText>(R.id.et_Country)
        val etpin=findViewById<EditText>(R.id.et_Pin)
        val etabout=findViewById<EditText>(R.id.et_about)
        val rg = findViewById<RadioGroup>(R.id.rg)
        val radioId =rg.checkedRadioButtonId
        val rb = findViewById<RadioButton>(radioId)
        val w = findViewById<CheckBox>(R.id.checkBox)
        val k = findViewById<CheckBox>(R.id.checkBox2)
        val pw = findViewById<CheckBox>(R.id.checkBox3)
        val p = findViewById<CheckBox>(R.id.checkBox4)
        val sp: ArrayList<String> = ArrayList()


        RegisterBtn.setOnClickListener {


            val database = Firebase.database
            var FName = etFName.text.toString()
            var LName = etLName.text.toString()
            val Email = etEmail.text.toString()
            val Mobile = etMobile.text.toString()
            val Password = etPassword.text.toString()
            val CPassword = etCPassword.text.toString()
            val City = etcity.text.toString()
            val State = etstate.text.toString()
            val Country = etcountry.text.toString()
            val Pin = etpin.text.toString()
            val Gender = rb.text.toString()
            val About = etabout.text.toString()



            //if(etFName!=null && etLName!=null && etEmail!=null && etMobile!=null && etPassword!=null && etCPassword!=null){
                if(Password == CPassword){
                    /*if (w.isChecked){
                        sp.add("Wedding")
                    }
                    if (k.isChecked){
                        sp.add("Kids Photography")
                    }
                    if (pw.isChecked){
                        sp.add("Pre Wedding")
                    }
                    if (p.isChecked){
                        sp.add("Potrait")
                    }*/
                    val User = Photographer(FName,LName,Email,Mobile,Password,"Male",City,State,Country,Pin,About)
                    var myRef = database.getReference("Photographers")
                    var data = myRef.child(Mobile)

                    data.setValue(User).addOnSuccessListener {

                        etFName.text.clear()
                        etLName.text.clear()
                        etEmail.text.clear()
                        etMobile.text.clear()
                        etPassword.text.clear()
                        etcity.text.clear()
                        etstate.text.clear()
                        etcountry.text.clear()
                        etpin.text.clear()
                        etabout.text.clear()

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
            /*}
            else{
                Toast.makeText(this,"Enter all information correctly!!",Toast.LENGTH_SHORT).show()
            }*/




        }

    }
    fun onLoginClick(view: View?) {
        startActivity(Intent(this, Login::class.java))
        overridePendingTransition(R.anim.slide_in_left, android.R.anim.slide_out_right)
    }

    }