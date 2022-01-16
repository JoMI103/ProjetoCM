package com.example.projetocm.GameSystem

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Rect
import com.example.projetocm.R
import java.util.*

class Chimneys {

    var bitmap : Bitmap
    var x : Float
    var y : Float
    var speed : Int = 0

    var maxY : Float
    var minY : Float
    var maxX : Float
    var minX : Float

    var detectColosion = Rect()

    constructor(context: Context, screenWidth: Int, screenHeight : Int){
        maxX = screenWidth.toFloat()
        minX = 0F
        maxY = screenHeight.toFloat()
        minY = 0F

        bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.chimney)

        var generator = Random()
        speed = 30
        y =  generator.nextInt(100) + (maxY / 2)
        x = -20f

        detectColosion = Rect(x.toInt(),y.toInt(),bitmap.width, bitmap.height)
    }

    fun update(bool: Boolean,yCord: Int,id: Int){

        x -= speed

        detectColosion.left = x.toInt()
        detectColosion.top = y.toInt()
        detectColosion.right = x.toInt() + bitmap.width
        detectColosion.bottom = y.toInt() + bitmap.height


        if(bool){
            if(id == 0)
                y = yCord + 250f
            else
                y = yCord - 250f - bitmap.height

            x = maxX
        }
    }
}

