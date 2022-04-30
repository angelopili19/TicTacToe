package com.example.tictactoe

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast


class PlayActivity : AppCompatActivity() , View.OnClickListener{

    private var turno:Int = 0
    private var win:Boolean = false
    private var pointX:Int = 0
    private var pointO:Int = 0
    private var caselle = mutableListOf<Button>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play)

        var extras = intent.extras
        if (extras != null) {
            pointO = extras.getInt("point_O")
            pointX = extras.getInt("point_X")
        }
        val tv :TextView = findViewById(R.id.table_point)
        tv.text = "Punteggio:\n-O = $pointO\n-X = $pointX"


        for (i in 1..9) {
            val resID = resources.getIdentifier("button_$i", "id", packageName)
            caselle.add(findViewById(resID))
        }
        caselle.forEach { it.setOnClickListener(this) }
    }

    override fun onClick(v:View?){

        var bool  = v is Button && v.text.toString().isNotEmpty()
        if(!bool){
            var player = if(turno%2 == 0) "O" else "X"
            var color = if (turno%2==0) "#1703fc" else "#fc0303"
            var nextplayer = if(turno%2 == 1) "Turno: O" else "Turno: X"
            when(v?.id){
                R.id.button_1 -> {
                    caselle[0].text = player
                    caselle[0].setTextColor(Color.parseColor(color))
                    win = checkWin3(caselle[0], caselle[1], caselle[2])
                            || checkWin3(caselle[0], caselle[3], caselle[6])
                            || checkWin3(caselle[0], caselle[4], caselle[8])
                }
                R.id.button_2 -> {
                    caselle[1].text = player
                    caselle[1].setTextColor(Color.parseColor(color))
                    win = checkWin3(caselle[0], caselle[1], caselle[2])
                            || checkWin3(caselle[1], caselle[4], caselle[7])
                }
                R.id.button_3 -> {
                    caselle[2].text = player
                    caselle[2].setTextColor(Color.parseColor(color))
                    win = checkWin3(caselle[0], caselle[1], caselle[2])
                            || checkWin3(caselle[2], caselle[5], caselle[8])
                            || checkWin3(caselle[2], caselle[4], caselle[6])
                }
                R.id.button_4 -> {
                    caselle[3].text = player
                    caselle[3].setTextColor(Color.parseColor(color))
                    win = checkWin3(caselle[3], caselle[4], caselle[5])
                            || checkWin3(caselle[0], caselle[3], caselle[6])
                }
                R.id.button_5 -> {
                    caselle[4].text = player
                    caselle[4].setTextColor(Color.parseColor(color))
                    win = checkWin3(caselle[3], caselle[4], caselle[5])
                            || checkWin3(caselle[1], caselle[4], caselle[7])
                            || checkWin3(caselle[0], caselle[4], caselle[8])
                            || checkWin3(caselle[2], caselle[4], caselle[6])
                }
                R.id.button_6 -> {
                    caselle[5].text = player
                    caselle[5].setTextColor(Color.parseColor(color))
                    win = checkWin3(caselle[3], caselle[4], caselle[5])
                            || checkWin3(caselle[2], caselle[5], caselle[8])
                }
                R.id.button_7 -> {
                    caselle[6].text = player
                    caselle[6].setTextColor(Color.parseColor(color))
                    win = checkWin3(caselle[6], caselle[7], caselle[8])
                            ||checkWin3(caselle[0], caselle[3], caselle[6])
                            ||checkWin3(caselle[2], caselle[4], caselle[6])
                }
                R.id.button_8 -> {
                    caselle[7].text = player
                    caselle[7].setTextColor(Color.parseColor(color))
                    win = checkWin3(caselle[1], caselle[4], caselle[7])
                            ||checkWin3(caselle[6], caselle[7], caselle[8])
                }
                R.id.button_9 -> {
                    caselle[8].text = player
                    caselle[8].setTextColor(Color.parseColor(color))
                    win = checkWin3(caselle[6], caselle[7], caselle[8])
                            ||checkWin3(caselle[2], caselle[5], caselle[8])
                            ||checkWin3(caselle[0], caselle[4], caselle[8])
                }
            }
            if(win){
                winner(player)
            }else{
                turno++
                if(turno == 9)
                    winner("")
                var tv:TextView = findViewById(R.id.turno)
                tv.text = nextplayer
            }
        }
        else{
            Toast.makeText(this, "Cella non selezionabile", Toast.LENGTH_LONG).show()
        }
    }

    private fun checkWin3(b1:Button, b2:Button, b3:Button): Boolean {
        var win :Boolean = (b1.text.equals(b2.text) && b1.text.equals(b3.text))
        if(win){
            b1.text = "W"
            b2.text = "I"
            b3.text = "N"
        }
        return win
    }

    private fun winner(winner:String){
        val intent:Intent = Intent(this, WinActivity::class.java).apply {
            putExtra("winner", winner)
            putExtra("point_X", pointX)
            putExtra("point_O", pointO)
        }
        startActivity(intent)
        finish()
    }
}

