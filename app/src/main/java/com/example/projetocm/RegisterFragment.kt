package com.example.projetocm

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.projetocm.databinding.FragmentRegisterBinding
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.util.*
import android.text.TextUtils

import android.R

import android.widget.EditText





class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    private lateinit var auth: FirebaseAuth

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        binding.editTextError.visibility = View.INVISIBLE
        val root: View = binding.root
        return root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = Firebase.auth

        binding.buttonCreate.setOnClickListener {
            binding.editTextError.visibility = View.INVISIBLE

            var notEmpty  = true


            if (TextUtils.isEmpty(binding.editTextEmailR.text.toString())){
                binding.editTextEmailR.error = "Your message"
                notEmpty = false }

            if (TextUtils.isEmpty(binding.editTextPasswordR.text.toString())){
                binding.editTextPasswordR.error = "Your message"
                notEmpty = false }

            if (TextUtils.isEmpty(binding.editTextRepeatPasswordR.text.toString())){
                binding.editTextRepeatPasswordR.error = "Your message"
                notEmpty = false }




            if(notEmpty)
            if(binding.editTextPasswordR.text.toString() == binding.editTextRepeatPasswordR.text.toString())
            {
                auth.createUserWithEmailAndPassword(binding.editTextEmailR.text.toString(), binding.editTextPasswordR.text.toString())
                    .addOnCompleteListener() { task ->
                        if (task.isSuccessful)
                        {
                            val user = hashMapOf("CreatedAccountDate" to Timestamp(Date()),)

                            Firebase.firestore.collection("users")
                                .document(FirebaseAuth.getInstance().uid.toString()).set(user)

                            findNavController().navigate(com.example.projetocm.R.id.action_registerFragment_to_loginFragment)
                        }
                        else
                        {
                            binding.editTextError.visibility = View.VISIBLE
                            binding.editTextError.text = "Email invalido"
                        }
                    }
            }
            else
            {
                binding.editTextError.visibility = View.VISIBLE
                binding.editTextError.text = "Passwords diferentes"
            }

        }
    }


}