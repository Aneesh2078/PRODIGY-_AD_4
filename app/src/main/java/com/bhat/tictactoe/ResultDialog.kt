package com.bhat.tictactoe

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class ResultDialog(context: Context, message: String?, mainActivity: MainActivity) :
    Dialog(context) {
    private val message: String?
    private val mainActivity: MainActivity

    init {
        this.message = message
        this.mainActivity = mainActivity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result_dialog)

        val messageText = findViewById<TextView>(R.id.messageText)
        val startAgainButton = findViewById<Button>(R.id.startAgainButton)

        messageText.text = message

        startAgainButton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                mainActivity.restartMatch()
                dismiss()
            }
        })
    }
}