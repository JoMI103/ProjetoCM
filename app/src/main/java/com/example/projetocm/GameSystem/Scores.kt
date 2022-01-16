package com.example.projetocm.GameSystem

import com.google.firebase.Timestamp

class Scores {


    var nome:String? = null
    var score:Long? = null

    constructor()

    constructor(nome: String?, score: Long?) {
        this.nome = nome
        this.score = score
    }

    fun toHashMap() : HashMap<String, Comparable<Any>?> {
        val post = hashMapOf(
            "Name" to nome,
            "Score" to score,

        )

        return post as HashMap<String, Comparable<Any>?>
    }

    companion object {
        fun fromHashMap(hash: Map<String, Any>) : Scores {
            var post = Scores()

            post.nome = hash["Name"] as String?
            post.score = hash["Score"] as Long?


            return post
        }
    }
}