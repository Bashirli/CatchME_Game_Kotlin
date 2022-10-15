package com.bashirli.catchmekotlin

import android.content.Intent
import android.content.SharedPreferences
import android.media.Image
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import com.bashirli.catchmekotlin.databinding.ActivityGameBinding
import java.util.*
import kotlin.collections.ArrayList


class GameActivity : AppCompatActivity() {
    var clicked=0
    var score =0
    lateinit var name :String
    lateinit var level:String
    lateinit var highname:String

    var highscore=0
    var handler=Handler()
    lateinit var sharedPreferences:SharedPreferences
    var runnable=Runnable{}
    var imageArray =ArrayList<ImageView>()

  private lateinit var binding: ActivityGameBinding




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
binding=ActivityGameBinding.inflate(layoutInflater)
        var view=binding.root
        setContentView(view)
        name= intent.getStringExtra("name").toString()
        level=intent.getStringExtra("level").toString()
 binding.textView6.text="Level : $level"

        sharedPreferences=this.getSharedPreferences("com.bashirli.catchmekotlin", MODE_PRIVATE)
highscore=sharedPreferences.getInt("highscore",0)
        highname= sharedPreferences.getString("highname","Guest").toString()
binding.textView8.text="High score : $highscore ($highname)"

        imageArray.add(binding.imageView)
        imageArray.add(binding.imageView2)
        imageArray.add(binding.imageView3)
        imageArray.add(binding.imageView4)
        imageArray.add(binding.imageView5)
        imageArray.add(binding.imageView6)
        imageArray.add(binding.imageView7)
        imageArray.add(binding.imageView8)
        imageArray.add(binding.imageView9)

        object :CountDownTimer(30500,1000){
            override fun onTick(p0: Long) {
                binding.textView7.text="Left seconds : ${p0/1000}"
                hideimages()

            }

            override fun onFinish() {
                handler.removeCallbacks(runnable)
                for(image in imageArray){
                    image.visibility=View.INVISIBLE

                }
                if(score>highscore){
                    highscore=score
                    sharedPreferences.edit().putString("highname",name).apply()
                sharedPreferences.edit().putInt("highscore",highscore).apply()
                }
                var alert=AlertDialog.Builder(this@GameActivity)
                alert.setTitle("Game Over !").setMessage(name+" , Do you want to restart the game?")
                    .setNegativeButton("No"){dialog,which->
                        var intent=Intent(this@GameActivity,ScreenActivity::class.java)
                        startActivity(intent)
                        finish()
                    }.setPositiveButton("Yes"){dialog,which->
                        var intent=intent
                        startActivity(intent)
                        finish()
                    }.show()


            }

        }.start()

    }
fun hideimages(){
runnable=object:Runnable{
    override fun run() {

        for (image in imageArray) {
            image.visibility = View.INVISIBLE
        }
        clicked = 0
        var random = Random()
        var ranIndex = random.nextInt(9)
        imageArray[ranIndex].visibility = View.VISIBLE
        if (level.equals("Easy")) {

            handler.postDelayed(runnable, 1000)
        }else if(level.equals("Normal")){
            handler.postDelayed(runnable, 570)

        }else if(level.equals("Hard")){
            handler.postDelayed(runnable, 300)

        }
    }
}
    handler.post(runnable)
}

    fun tapImage(view: View) {
        if (clicked == 0) {
            score++
            binding.textView5.text = "Score : $score"
if(score>highscore){
    binding.textView8.text="Highscore : $score ($name)"
}
        clicked=1
        }
    }

}