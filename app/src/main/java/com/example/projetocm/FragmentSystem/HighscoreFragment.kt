package com.example.projetocm.FragmentSystem

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.projetocm.GameSystem.Scores
import com.example.projetocm.R
import com.example.projetocm.databinding.FragmentHighscoreBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.firestore.DocumentSnapshot

import com.google.firebase.firestore.QuerySnapshot

import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.toObject
import kotlin.math.log


class  HighscoreFragment : Fragment() {

    private var _binding: FragmentHighscoreBinding? = null
    private val binding get() = _binding!!

    lateinit var listviewScores: ListView
    private  var scoresList = mutableListOf<Scores>()

    //private val Scoress = ArrayList<Int>()
    //lateinit var listviewPositions: ListView
    //private val scores = ArrayList<Int>()

    val adapter = PostsAdapter()


  override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {




      val db = Firebase.firestore

/*
      db.collection("products").get()
          .addOnSuccessListener { queryDocumentSnapshots ->
              progressBar.setVisibility(View.GONE)
              if (!queryDocumentSnapshots.isEmpty) {
                  val list = queryDocumentSnapshots.documents
                  for (d in list) {
                      val p: Product = d.toObject(Product::class.java)
                      p.setId(d.id)
                      productList.add(p)
                  }
                  adapter.notifyDataSetChanged()
              }
          }

 */
/*
      db.collection("Scores")
          //.whereEqualTo("user", FirebaseAuth.getInstance().currentUser.uid)
          .orderBy("Score", Query.Direction.DESCENDING).limit(2)
          .addSnapshotListener { value, e ->
              if (e != null) {
                  return@addSnapshotListener
              }
              //scoresList.clear()
              val post = value.toObjects<Scores>()
              for (scr in value!!.documents) {

                  val post = Scores.fromHashMap(scr.data!!)

                  scoresList.add(Scores(post.nome,post.score))
                 // Log.e(value.toString(),post.nome.toString())
              }

          }


 */
      db.collection("Scores")
          .orderBy("Score", Query.Direction.DESCENDING).limit(10)
          .get()
          .addOnSuccessListener { documents ->
              for (document in documents) {
                  val post = Scores.fromHashMap(document.data)


                  val name = document.data?.let { it["Name"] as String? }
                  val score = document.data?.let { it["Score"] as Long? }


                   scoresList.add(Scores(name,score))
              }

              adapter.notifyDataSetChanged()
          }
          .addOnFailureListener { exception ->
              Log.w(TAG, "Error getting documents: ", exception)
          }



      //scoresList.add(Scores("ok",10))
/*
      db.collection("Scores").orderBy("Score").limit(10).get()
          .addOnSuccessListener { queryDocumentSnapshots ->
              if (!queryDocumentSnapshots.isEmpty) {
                  val list = queryDocumentSnapshots.documents
                  for (d in list) {

                          scoresList.add(d.toObject(Scores::class.java)!!)

                  }

              }
          }


 */


        _binding = FragmentHighscoreBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root

    }
    inner class PostsAdapter : BaseAdapter() {
        override fun getCount(): Int {
            return scoresList.size
        }

        override fun getItem(position: Int): Any {
            return scoresList[position]
        }

        override fun getItemId(p0: Int): Long {
            return 0L
        }

        override fun getView(position: Int, view: View?, viewGroup: ViewGroup?): View {
            val rootView = layoutInflater.inflate(R.layout.row_score, viewGroup, false)
            val textviewId= rootView.findViewById<TextView>(R.id.Name)
            val textViewScore = rootView.findViewById<TextView>(R.id.Score)


            textviewId.text = scoresList[position].nome
            textViewScore.text = scoresList[position].score.toString()

            return rootView
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listviewScores = binding.listviewScores

        listviewScores.adapter = adapter



    }
}

