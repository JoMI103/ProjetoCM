package com.example.projetocm

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.projetocm.databinding.FragmentLoginBinding
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.text.SimpleDateFormat
import java.util.*

class  LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!



    private lateinit var auth: FirebaseAuth


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = Firebase.auth

        binding.buttonLogin.setOnClickListener {
            binding.textViewError.visibility = View.INVISIBLE


            auth.signInWithEmailAndPassword(binding.editTextEmail.text.toString(), binding.editTextPassword.text.toString())
                .addOnCompleteListener() { task ->
                    if (task.isSuccessful)
                    {
                        val user = hashMapOf("online" to true, "date" to Timestamp(Date()),)

                        Firebase.firestore.collection("users")
                            .document(FirebaseAuth.getInstance().uid.toString()).set(user,
                                SetOptions.merge())

                        findNavController().navigate(R.id.action_loginFragment_to_menuFragment)
                    }
                    else
                    {
                        binding.textViewError.visibility = View.VISIBLE
                        binding.textViewError.text = "Password errada ou falha de internet"
                    }
                }
        }
    }
}



