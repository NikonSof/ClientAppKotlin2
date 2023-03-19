package com.nikonsoft.clientappkotlin

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.Auth
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.nikonsoft.clientappkotlin.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        auth = FirebaseAuth.getInstance()

        binding.bSignIn.setOnClickListener {
            val email = binding.edName.text.toString()
            val pass = binding.edPassword.text.toString()

            if (email.isNotEmpty() && pass.isNotEmpty()) {

                auth.signInWithEmailAndPassword(email, pass).addOnCompleteListener {
                    if (it.isSuccessful) {
                        Toast.makeText(this, "Авторизация успешна", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, Hub::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(this, "Авторизация провалена", Toast.LENGTH_SHORT).show()

                    }
                }
            } else {
                Toast.makeText(this, "Пустые поля запрещены !!", Toast.LENGTH_SHORT).show()

            }
        }

    }
    override fun onStart() {
        super.onStart()

        if (auth.currentUser != null) {
            val intent = Intent(this, Hub::class.java)
            startActivity(intent)
        }
    }

}
