package com.example.projetocm.GameSystem

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.projetocm.MainActivity
import com.example.projetocm.R
import com.example.projetocm.databinding.ActivityRestartBinding
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.util.*

class RestartGame : AppCompatActivity() {

     //private var _binding: ActivityRestartBinding? = null
     // private val binding get() = _binding!!
    private lateinit var binding: ActivityRestartBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_restart)
        binding = ActivityRestartBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.tvScore.text = "Score: " + intent.getStringExtra("score")


        binding.buttonBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        binding.buttonTryAgain.setOnClickListener {
            val intent = Intent(this, GameActivity::class.java)
            startActivity(intent)
        }

        val db = Firebase.firestore
        val score = hashMapOf(
            "Score" to 10,
            "Time" to Timestamp(Date()),
        )
        if(true){

            db.collection("Scores")
                .document("FirebaseAuth.getInstance().uid.toString()")
                .set(score)
        }
        else
        {
            db.collection("Scores")
                .document("unknown")
                .set(score)
        }


    }




/*
    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {


        binding.tvScore.text = "Score: " + intent.getStringExtra("score")
binding.buttonBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
       }

        binding.buttonBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        binding.buttonTryAgain.setOnClickListener {
            val intent = Intent(this, GameActivity::class.java)
            startActivity(intent)
        }

        return super.onCreateView(name, context, attrs)
    }

 */

}