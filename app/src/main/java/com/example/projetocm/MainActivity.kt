package com.example.projetocm


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.example.projetocm.databinding.NavHostBinding
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.util.*


class MainActivity : AppCompatActivity() {

    private lateinit var binding: NavHostBinding

    val db = Firebase.firestore

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.nav_host)


    }



    override fun onResume() {
        super.onResume()
        val user = hashMapOf(
            "online" to true,
            "date" to Timestamp(Date()),
        )
        db.collection("users")
            .document(FirebaseAuth.getInstance().uid.toString())
            .set(user, SetOptions.merge())
    }

    override fun onPause() {
        super.onPause()

        // val currentUser = auth.currentUser
        //if(currentUser != null){
        val user = hashMapOf(
            "online" to false,
            "date" to Timestamp(Date()),
        )
        db.collection("users")
            .document(FirebaseAuth.getInstance().uid.toString())
            .set(user,SetOptions.merge())


        // }
    }


    override fun onDestroy() {
        super.onDestroy()


        Firebase.auth.signOut()
    }


}