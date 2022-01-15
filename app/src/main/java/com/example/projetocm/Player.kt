package com.example.projetocm

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Rect

class Player {

    var bitmap : Bitmap
    var x : Float
    var y : Float
    var speed : Int = 0

    var boosting = false

    var maxY : Float
    var minY : Float

    var detectColosion = Rect()

    companion object{
        const val GRAVITY = 10
    }

    constructor(context: Context, screenWidth: Int, screenHeight : Int){
        x = 75f
        y = 50f
        speed = 1
        bitmap = BitmapFactory
            .decodeResource(context.resources,R.drawable.player)

        minY = 0F
        maxY = (screenHeight - bitmap.height).toFloat()
        detectColosion = Rect(x.toInt(),y.toInt(),bitmap.width, bitmap.height)
    }

    fun update(){

        y -= speed - GRAVITY

        if (y < minY) y = minY
        if (y > maxY) y = maxY

        detectColosion.left = x.toInt()
        detectColosion.top = y.toInt()
        detectColosion.right = x.toInt() + bitmap.width
        detectColosion.bottom = y.toInt() + bitmap.height
    }

}