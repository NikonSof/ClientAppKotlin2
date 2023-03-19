package com.nikonsoft.clientappkotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class Hub : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hub2)

        val btn = findViewById<View>(R.id.chat_button) as Button
        val btn1 = findViewById<View>(R.id.tasks_button) as Button
        val btn2 = findViewById<View>(R.id.exit_button) as Button




        btn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        btn1.setOnClickListener {
            val intent = Intent(this, KanBoard::class.java)
            startActivity(intent)
        }

        btn2.setOnClickListener {
            Firebase.auth.signOut()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }


    }

}