package com.bashirli.catchmekotlin

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.bashirli.catchmekotlin.databinding.ActivityScreenBinding

class ScreenActivity : AppCompatActivity() {
    lateinit var level:String
    private lateinit var binding: ActivityScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityScreenBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

    }

fun problem_tapma():Int{
    if(!(binding.radioButton.isChecked||
            binding.radioButton2.isChecked||
            binding.radioButton3.isChecked)){
        Toast.makeText(applicationContext,"Level did not choosen.",Toast.LENGTH_SHORT).show()
        return 0
    }



    return 1
}

    fun play(view: View){
        if(problem_tapma()==0){
            return
        }

    if(binding.radioButton.isChecked){
        level="Easy"
    }else
        if(binding.radioButton2.isChecked){
level="Normal"
        }else
            if(binding.radioButton3.isChecked){
level="Hard"
            }

        var name=binding.editTextTextPersonName.text.toString()
        if(name.equals("")) {
            var alert: AlertDialog.Builder = AlertDialog.Builder(this)
            alert.setTitle("ATTENTION!")
                .setMessage("You have not a name for a game. \n Do you want to continue like a guest?");
            alert.setNegativeButton("No") { dialog, which ->
                Toast.makeText(this, "Returned", Toast.LENGTH_LONG).show();

                return@setNegativeButton

            }.setPositiveButton("Yes") { dialog, which ->
                Toast.makeText(this, "Game is starting...", Toast.LENGTH_SHORT).show();
                if (name.equals("")) {
                    name = "Guest"
                }
                val intent = Intent(this@ScreenActivity, GameActivity::class.java)
                intent.putExtra("name", name)
                intent.putExtra("level", level)
                startActivity(intent)
                finish()
            }.show()
        }else {
            val intent = Intent(this@ScreenActivity, GameActivity::class.java)
            intent.putExtra("name", name)
            intent.putExtra("level", level)
            startActivity(intent)
            finish()
        }
        }

}