package com.example.tasbeh

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.media.MediaPlayer
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.VibrationEffect
import android.view.LayoutInflater
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.stopper_dialog.view.*
import android.os.Vibrator as Vibrator

class MainActivity : AppCompatActivity() {

    lateinit var sharedPreferences: SharedPreferences
    lateinit var sharedPreferences2: SharedPreferences
    var count=0
    var darktheme=false
    var checkVibr=false
    var stopperNumber=33
    @RequiresApi(Build.VERSION_CODES.N)
    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)





        if (MyObject.color != null) {
            display_counter.background=MyObject.color
        }

        menu_theme.setOnClickListener {
            if (!darktheme){
                main_display.setBackgroundColor(Color.rgb(0,0,0))
                navbar.setBackgroundColor(Color.rgb(0,0,0))
                darktheme=true
            }else{
                main_display.setBackgroundColor(Color.rgb(52,72,83))
                navbar.setBackgroundColor(Color.rgb(70,90,101))
                darktheme=false
            }
        }

        menu_color.setOnClickListener {
            val intent=Intent(this@MainActivity, MainActivity2::class.java)
            startActivity(intent)
        }


        sharedPreferences=getSharedPreferences("tasbex_count", Context.MODE_PRIVATE)

        count=sharedPreferences.getInt("keyCount", 0)
        number.text=count.toString()

        val editor=sharedPreferences.edit()



        main_btn.setOnClickListener {
            count++
            if (count%stopperNumber==0){
                val vibr=(getSystemService(Context.VIBRATOR_SERVICE) as Vibrator)
                if (checkVibr){
                    if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
                        vibr.vibrate(VibrationEffect.createOneShot(500,VibrationEffect.DEFAULT_AMPLITUDE))
                    }else{
                        vibr.vibrate(500)
                    }
                }
                Toast.makeText( this, "Stopper value reached !!!", Toast.LENGTH_SHORT).show()
            }
            editor.putInt("keyCount", count)
            editor.apply()
            number.text=count.toString()
            val vibr=(getSystemService(Context.VIBRATOR_SERVICE) as Vibrator)
            if (checkVibr){
                if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
                    vibr.vibrate(VibrationEffect.createOneShot(200,VibrationEffect.DEFAULT_AMPLITUDE))
                }else{
                    vibr.vibrate(200)
                }
            }

        }

        rate_us.setOnClickListener {
            val intent=Intent(Intent.ACTION_VIEW)
            intent.setData(Uri.parse("https://kun.uz/"))
            startActivity(intent)
        }

        reset_btn.setOnClickListener {
            count=0
            editor.putInt("keyCount", count)
            editor.apply()
            number.text=count.toString()
        }



        vibrator.setOnClickListener {
            if (!checkVibr){
                vibrator.setBackgroundResource(R.drawable.footer_background_active)
                checkVibr=true
            }else{
                vibrator.setBackgroundResource(R.drawable.footer_background)
                checkVibr=false
            }
        }






        sharedPreferences2=getSharedPreferences("stopper_count", Context.MODE_PRIVATE)
        val editor2=sharedPreferences2.edit()
        stopperNumber=sharedPreferences2.getInt("keyStopper", 33)
        stopper_number.text=stopperNumber.toString()


        stopper.setOnClickListener {
            //inflate dialog with view
            val mDialogView=LayoutInflater.from(this).inflate(R.layout.stopper_dialog, null)

            //AlertDialog builder
            val mBuilder=AlertDialog.Builder(this)
                .setView(mDialogView)
                .setTitle("Enter stopper limit: ")

            //show dialog
            val mAlertDialog=mBuilder.show()
            //ok button click




            mDialogView.ok_btn.setOnClickListener {
                //dismiss alertDialog
                if (mDialogView.new_stopper_number.text.isEmpty() || mDialogView.new_stopper_number.text.toString().first()=='0'){
                    Toast.makeText(this, "Son kiriting !!", Toast.LENGTH_SHORT).show()
                }else{
                    mAlertDialog.dismiss()
                    //get Stopper from Alert
                    val numStopper=mDialogView.new_stopper_number.text.toString()
                    stopperNumber=numStopper.toInt()
                    editor2.putInt("keyStopper", stopperNumber)
                    editor2.apply()
                    stopper_number.setText("$stopperNumber")
                }

            }
            mDialogView.cancel_btn.setOnClickListener {
                mAlertDialog.dismiss()
            }
        }

    }

    override fun onStart() {
        super.onStart()
        setContentView(main_display)
        if (MyObject.color != null) {
            display_counter.background=MyObject.color
        }

    }




    }




