package com.example.projetocm.GameSystem

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView
import com.example.projetocm.R

class GameView: SurfaceView,Runnable {

    var playing = false
    lateinit var gameThread : Thread

    lateinit var surfaceHolder: SurfaceHolder
    var canvas : Canvas? =  null
    lateinit var paint : Paint
    lateinit var player : Player


    constructor(context: Context?,
                screenWidth : Int,
                screenHeight:Int) : super(context){
        init(context,
            screenWidth,
            screenHeight)


    }

    /*constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs){
        init(context)
    }
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ){
        init(context)
    }

     */

    fun init(context: Context?,
             screenWidth : Int = 0,
             screenHeight: Int = 0){
        surfaceHolder = holder
        player = Player(context!!, screenWidth, screenHeight)
        paint = Paint()
    }


    override fun run() {
        while(playing){
            update()
            draw()
            control()
        }
    }

    fun resume(){
        playing = true
        gameThread = Thread(this)
        gameThread.start()
    }

    fun pause() {
        playing = false
        gameThread.join()
    }

    fun update(){

        player.update()

    }

    fun draw(){
        if (surfaceHolder.surface.isValid){
            canvas = surfaceHolder.lockCanvas()
            canvas?.drawColor(Color.BLACK)

            canvas?.drawBitmap(player.bitmap, player.x, player.y, paint)
            //paint.style = Paint.Style.STROKE
            //canvas?.drawRect(player.detectColosion,paint)

            surfaceHolder.unlockCanvasAndPost(canvas)
        }
    }

    fun control(){
        Thread.sleep(17)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        event?.let{
            when (it.action.and(MotionEvent.ACTION_MASK)){
                /*MotionEvent.ACTION_UP ->{
                    player.boosting = false
                }
                 */
                MotionEvent.ACTION_DOWN ->{
                    if(player.cooldown == 0){
                    player.forca = 70
                    player.cooldown = 4
                }
                }
            }
        }
        return true
    }
}