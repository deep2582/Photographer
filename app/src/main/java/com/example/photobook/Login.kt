package com.example.photobook

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    fun onLoginClick(view: View?) {
        startActivity(Intent(this, Register::class.java))
        overridePendingTransition(R.anim.slide_in_right, android.R.anim.slide_out_right)
    }
}