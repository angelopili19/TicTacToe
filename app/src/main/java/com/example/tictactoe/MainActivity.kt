package com.example.tictactoe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

lateinit var button:Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button = findViewById(R.id.gioca)

        button.setOnClickListener {
            val intent: Intent = Intent(this, PlayActivity::class.java).apply {
                putExtra("point_O", 0)
                putExtra("point_X", 0)
            }
            startActivity(intent)
        }
    }

}
