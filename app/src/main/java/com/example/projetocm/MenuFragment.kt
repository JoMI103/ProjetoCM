package com.example.projetocm


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.projetocm.databinding.FragmentMenuBinding
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.util.*

class MenuFragment : Fragment(){

    private var _binding: FragmentMenuBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {



        _binding = FragmentMenuBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root


    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.PlayButton.setOnClickListener{
            findNavController().navigate(R.id.action_menuFragment_to_playerFragment)
        }

        binding.LoginButton.setOnClickListener {
            findNavController().navigate(R.id.action_menuFragment_to_loginFragment)
        }

        binding.RegisterButton.setOnClickListener {
            findNavController().navigate(R.id.action_menuFragment_to_registerFragment)
        }

        binding.LogoutButton.setOnClickListener {

            if(Firebase.auth.currentUser != null) {
                val user = hashMapOf(
                    "online" to false,
                    "date" to Timestamp(Date()),
                )
                Firebase.firestore.collection("users")
                    .document(FirebaseAuth.getInstance().uid.toString())
                    .set(user, SetOptions.merge())


                Firebase.auth.signOut()
                binding.LoginButton.isEnabled = true
                binding.RegisterButton.isEnabled = true
                binding.LogoutButton.isEnabled = false
            }
        }



        if(Firebase.auth.currentUser != null){
            binding.LoginButton.isEnabled = false
            binding.RegisterButton.isEnabled = false
            binding.LogoutButton.isEnabled = true
        }else{
            binding.LoginButton.isEnabled = true
            binding.RegisterButton.isEnabled = true
            binding.LogoutButton.isEnabled = false
        }

    }
}