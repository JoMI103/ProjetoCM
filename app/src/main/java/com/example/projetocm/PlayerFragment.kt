package com.example.projetocm

import android.graphics.Point
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.projetocm.databinding.FragmentPlayerBinding
import androidx.appcompat.app.AppCompatActivity



class PlayerFragment : Fragment() {

    private var _binding: FragmentPlayerBinding? = null
    private val binding get() = _binding!!
    private lateinit var gameView : GameView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //val display = windowManager.defaultDisplay
        var size = Point()
        //display.getSize(size)
        //gameView = GameView(this, size.x, size.y)
        //setContentView(gameView)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        _binding = FragmentPlayerBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root

    }
    override fun onResume() {
        super.onResume()
        gameView.resume()
    }

    override fun onPause() {
        super.onPause()
        gameView.pause()
    }

}




