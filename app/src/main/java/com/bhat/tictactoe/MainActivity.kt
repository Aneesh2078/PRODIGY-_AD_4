package com.bhat.tictactoe

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bhat.tictactoe.databinding.ActivityMainBinding
import java.util.ArrayList

class MainActivity : AppCompatActivity() {
    var binding: ActivityMainBinding? = null
    private val combinationList: MutableList<IntArray> = ArrayList<IntArray>()
    private var boxPositions = intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0)
    private var playerTurn = 1
    private var totalSelectedBoxes = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.getRoot())

        combinationList.add(intArrayOf(0, 1, 2))
        combinationList.add(intArrayOf(3, 4, 5))
        combinationList.add(intArrayOf(6, 7, 8))
        combinationList.add(intArrayOf(0, 3, 6))
        combinationList.add(intArrayOf(1, 4, 7))
        combinationList.add(intArrayOf(2, 5, 8))
        combinationList.add(intArrayOf(2, 4, 6))
        combinationList.add(intArrayOf(0, 4, 8))

        val getPlayerOneName = intent.getStringExtra("playerOne")
        val getPlayerTwoName = intent.getStringExtra("playerTwo")
        binding!!.playerOneName.text = getPlayerOneName
        binding!!.playerTwoName.text = getPlayerTwoName

        setGameCellClickListener(binding!!.image1, 0)
        setGameCellClickListener(binding!!.image2, 1)
        setGameCellClickListener(binding!!.image3, 2)
        setGameCellClickListener(binding!!.image4, 3)
        setGameCellClickListener(binding!!.image5, 4)
        setGameCellClickListener(binding!!.image6, 5)
        setGameCellClickListener(binding!!.image7, 6)
        setGameCellClickListener(binding!!.image8, 7)
        setGameCellClickListener(binding!!.image9, 8)

        val resetButton = findViewById<Button>(R.id.resetButton)
        resetButton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                restartMatch()
            }
        })
    }

    private fun setGameCellClickListener(imageView: ImageView, position: Int) {
        imageView.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                if (isBoxSelectable(position)) {
                    performAction(imageView, position)
                }
            }
        })
    }

    private fun performAction(imageView: ImageView, selectedBoxPosition: Int) {
        boxPositions[selectedBoxPosition] = playerTurn
        totalSelectedBoxes++

        if (playerTurn == 1) {
            imageView.setImageResource(R.drawable.ximage)
            if (checkResults()) {
                val resultDialog = ResultDialog(
                    this@MainActivity, binding!!.playerOneName.getText().toString()
                            + " is a Winner!", this@MainActivity
                )
                resultDialog.setCancelable(false)
                resultDialog.show()
                return  // Stop further processing
            }
        } else {
            imageView.setImageResource(R.drawable.oimage)
            if (checkResults()) {
                val resultDialog = ResultDialog(
                    this@MainActivity, binding!!.playerTwoName.getText().toString()
                            + " is a Winner!", this@MainActivity
                )
                resultDialog.setCancelable(false)
                resultDialog.show()
                return  // Stop further processing
            }
        }

        if (totalSelectedBoxes == 9) {
            val resultDialog = ResultDialog(this@MainActivity, "Match Draw", this@MainActivity)
            resultDialog.setCancelable(false)
            resultDialog.show()
            return  // Stop further processing
        }

        changePlayerTurn(if (playerTurn == 1) 2 else 1)
    }

    private fun changePlayerTurn(currentPlayerTurn: Int) {
        playerTurn = currentPlayerTurn
        if (playerTurn == 1) {
            binding!!.playerOneLayout.setBackgroundResource(R.drawable.black_border)
            binding!!.playerTwoLayout.setBackgroundResource(R.drawable.white_box)
        } else {
            binding!!.playerTwoLayout.setBackgroundResource(R.drawable.black_border)
            binding!!.playerOneLayout.setBackgroundResource(R.drawable.white_box)
        }
    }

    private fun checkResults(): Boolean {
        for (i in combinationList.indices) {
            val combination = combinationList.get(i)
            if (boxPositions[combination[0]] == playerTurn && boxPositions[combination[1]] == playerTurn && boxPositions[combination[2]] == playerTurn) {
                return true
            }
        }
        return false
    }

    private fun isBoxSelectable(boxPosition: Int): Boolean {
        return boxPositions[boxPosition] == 0
    }

    fun restartMatch() {
        boxPositions = intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0)
        playerTurn = 1
        totalSelectedBoxes = 0

        binding!!.image1.setImageResource(R.drawable.white_box)
        binding!!.image2.setImageResource(R.drawable.white_box)
        binding!!.image3.setImageResource(R.drawable.white_box)
        binding!!.image4.setImageResource(R.drawable.white_box)
        binding!!.image5.setImageResource(R.drawable.white_box)
        binding!!.image6.setImageResource(R.drawable.white_box)
        binding!!.image7.setImageResource(R.drawable.white_box)
        binding!!.image8.setImageResource(R.drawable.white_box)
        binding!!.image9.setImageResource(R.drawable.white_box)

        binding!!.playerOneLayout.setBackgroundResource(R.drawable.black_border)
        binding!!.playerTwoLayout.setBackgroundResource(R.drawable.white_box)
    }
}
