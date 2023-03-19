package com.nikonsoft.clientappkotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.auth.api.Auth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.nikonsoft.clientappkotlin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var adapter: UserAdapter
    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = Firebase.auth

        val database = Firebase.database;
        val myRef = database.getReference("messages")
        binding.bsend.setOnClickListener{
            myRef.child(myRef.push().key?: "Что-то пошло не так").setValue(User(auth.currentUser?.displayName, binding.edMessage.text.toString()))

        }
        onChangeListenter(myRef)
        initRcView()

    }
    private fun initRcView() = with(binding){
        adapter = UserAdapter()
        rcView.layoutManager = LinearLayoutManager(this@MainActivity)
        rcView.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.sign_out){
                Firebase.auth.signOut()
            val intent = Intent (this, LoginActivity::class.java)
            startActivity(intent)

        }
        return super.onOptionsItemSelected(item)
    }

    private fun onChangeListenter(dRef: DatabaseReference){
        dRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val list = ArrayList<User>()
               for (s in snapshot.children){
                    val user = s.getValue(User::class.java)
                    if(user!= null)list.add(user)
                }
                adapter.submitList(list)
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}