package com.bhat.tictactoe

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class AddPlayers : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_players)

        val playerOne = findViewById<EditText>(R.id.playerOne)
        val playerTwo = findViewById<EditText>(R.id.playerTwo)
        val startGameButton = findViewById<Button>(R.id.startGameButton)

        startGameButton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                val getPlayerOneName = playerOne.getText().toString()
                val getPlayerTwoName = playerTwo.getText().toString()

                if (getPlayerOneName.isEmpty() || getPlayerTwoName.isEmpty()) {
                    Toast.makeText(this@AddPlayers, "Please enter player name", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    val intent = Intent(this@AddPlayers, MainActivity::class.java)
                    intent.putExtra("playerOne", getPlayerOneName)
                    intent.putExtra("playerTwo", getPlayerTwoName)
                    startActivity(intent)
                }
            }
        })
    }
}