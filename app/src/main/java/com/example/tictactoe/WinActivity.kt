package com.example.tictactoe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class WinActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_win)
        var pointO = 0
        var pointX = 0
        var extras = intent.extras
        val whoWin = extras?.getString("winner")
        val winner = if(extras?.getString("winner").isNullOrBlank() ||extras?.getString("winner").isNullOrEmpty()) "Draw" else "Winner\n\n${extras?.getString("winner")}"
        if (extras != null) {
            pointO = extras.getInt("point_O")
            pointX = extras.getInt("point_X")
            if (whoWin.equals("X")){
                pointX ++
            }else if (whoWin.equals("O"))
                pointO++
        }

        var tv :TextView = findViewById(R.id.player)
        tv.text = winner
        button = findViewById(R.id.play_again)

        button.setOnClickListener {
            val intent: Intent = Intent(this, PlayActivity::class.java).apply {
                putExtra("point_X", pointX)
                putExtra("point_O", pointO)
            }
            startActivity(intent)
            finish()
        }
    }
}