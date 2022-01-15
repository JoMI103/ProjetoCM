package com.example.projetocm.GameSystem

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Rect
import com.example.projetocm.R

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
        const val MAX_SPEED = 20
        const val MIN_SPEED = 1
    }

    constructor(context: Context, screenWidth: Int, screenHeight : Int){
        // posicao do player no ecra
        x = 75f
        y = 50f
        speed = 1
        bitmap = BitmapFactory.decodeResource(context.resources,R.drawable.playerf)
        // posicoes de maximo e minimo no ecra
        minY = 0F
        maxY = (screenHeight - bitmap.height * 2).toFloat()
        detectColosion = Rect(x.toInt(),y.toInt(),bitmap.width, bitmap.height)
    }

    fun update(){
        if (boosting){
            speed += 2
        }else{
            speed -= 5
        }
        if (speed > MAX_SPEED) speed = MAX_SPEED
        if (speed < MIN_SPEED) speed = MIN_SPEED

        y -= speed - GRAVITY

        if (y < minY) y = minY
        if (y > maxY) y = maxY

        detectColosion.left = x.toInt()
        detectColosion.top = y.toInt()
        detectColosion.right = x.toInt() + bitmap.width
        detectColosion.bottom = y.toInt() + bitmap.height
    }

}