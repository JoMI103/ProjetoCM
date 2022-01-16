package com.example.projetocm.FragmentSystem

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.example.projetocm.R
import com.example.projetocm.databinding.FragmentHighscoreBinding
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.util.*
import java.util.jar.Attributes
import kotlin.collections.ArrayList

class  HighscoreFragment : Fragment() {

    private var _binding: FragmentHighscoreBinding? = null
    private val binding get() = _binding!!
    private val names = ArrayList<Int>()
    lateinit var listviewScores: ListView

  override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {



        _binding = FragmentHighscoreBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root

    }
    inner class PostsAdapter : BaseAdapter() {
        override fun getCount(): Int {
            return names.size
        }

        override fun getItem(position: Int): Any {
            return names[position]
        }

        override fun getItemId(p0: Int): Long {
            return 0L
        }

        override fun getView(position: Int, view: View?, viewGroup: ViewGroup?): View {
            val rootView = layoutInflater.inflate(R.layout.row_score, viewGroup, false)
            val textviewTitle = rootView.findViewById<TextView>(R.id.Name)
            val textViewDescription = rootView.findViewById<TextView>(R.id.Score)


            textviewTitle.text = names[position].toString()

            return rootView
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        names.add(123)
        names.add(144)

        listviewScores = binding.listviewScores
        val adapter = PostsAdapter()
        listviewScores.adapter = adapter


    }
}

