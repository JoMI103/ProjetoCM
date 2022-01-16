package com.example.projetocm.FragmentSystem

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.TextView
import com.example.projetocm.R
import com.example.projetocm.databinding.FragmentHighscoreBinding
import kotlin.collections.ArrayList

class  HighscoreFragment : Fragment() {

    private var _binding: FragmentHighscoreBinding? = null
    private val binding get() = _binding!!
    private val scores = ArrayList<Int>()
    lateinit var listviewScores: ListView

    lateinit var listviewPositions: ListView

  override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

      scores.add(29)
      scores.add(39)

        _binding = FragmentHighscoreBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root

    }
    inner class PostsAdapter : BaseAdapter() {
        override fun getCount(): Int {
            return scores.size
        }

        override fun getItem(position: Int): Any {
            return scores[position]
        }

        override fun getItemId(p0: Int): Long {
            return 0L
        }

        override fun getView(position: Int, view: View?, viewGroup: ViewGroup?): View {
            val rootView = layoutInflater.inflate(R.layout.row_score, viewGroup, false)
            val textviewId= rootView.findViewById<TextView>(R.id.Name)
            val textViewScore = rootView.findViewById<TextView>(R.id.Score)


            textviewId.text = (position+1).toString() + ": "
            textViewScore.text = scores[position].toString() + " Points"

            return rootView
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        listviewScores = binding.listviewScores
        val adapter = PostsAdapter()
        listviewScores.adapter = adapter


    }
}

