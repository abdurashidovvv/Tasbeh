package com.example.tasbeh

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import kotlinx.android.synthetic.main.activity_main2.*

class MainActivity2 : AppCompatActivity() {

    private lateinit var bitmap: Bitmap
    var hext=""
    var rgb=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        colorpicker.isDrawingCacheEnabled=true
        colorpicker.buildDrawingCache(true)

        colorpicker.setOnTouchListener { v, event ->
            if (event.action==MotionEvent.ACTION_DOWN || event.action == MotionEvent.ACTION_MOVE) {
                bitmap = colorpicker.drawingCache
                val pixel = bitmap.getPixel(event.x.toInt(), event.y.toInt())
                val r = Color.red(pixel)
                val g = Color.green(pixel)
                val b = Color.blue(pixel)

                hext = "#" + Integer.toHexString(pixel)

                rgb = "$r$g$b"
                activity2_display.setBackgroundColor(Color.rgb(r, g, b))
                MyObject.color=activity2_display.background
            }
            true}

    }


}