package com.example.zmh.tictactoegame

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import android.content.DialogInterface
import android.os.Build





class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun btnClick(view: View) {
        val btnSelected = view as Button
        var cellID = 0

        when (btnSelected.id) {
            R.id.btn1 -> cellID = 1
            R.id.btn2 -> cellID = 2
            R.id.btn3 -> cellID = 3
            R.id.btn4 -> cellID = 4
            R.id.btn5 -> cellID = 5
            R.id.btn6 -> cellID = 6
            R.id.btn7 -> cellID = 7
            R.id.btn8 -> cellID = 8
            R.id.btn9 -> cellID = 9
        }

        playGame(cellID, btnSelected)
    }

    var player1 = ArrayList<Int>()
    var player2 = ArrayList<Int>()

    var activePlayer = 1

    var builder: AlertDialog.Builder? = null

    private fun playGame(cellID: Int, btnSelected: Button) {
        if (activePlayer == 1) {
            btnSelected.text = "X"
            btnSelected.setBackgroundColor(Color.parseColor("#FF7FFD09"))
            player1.add(cellID)
            activePlayer = 2
            AutoPlay()
        } else {
            btnSelected.text = "O"
            btnSelected.setBackgroundColor(Color.parseColor("#FFFFFF"))
            player2.add(cellID)
            activePlayer = 1
        }
        btnSelected.isEnabled = false
        checkWinner()
    }

    private fun checkWinner() {
        var winner = -1
// win by full row
        if ((player1.contains(1) && player1.contains(2) && player1.contains(3))
                || (player1.contains(4) && player1.contains(5) && player1.contains(6))
                || (player1.contains(7) && player1.contains(8) && player1.contains(9)))
        {
            winner = 1
        }

        if ((player2.contains(1) && player2.contains(2) && player2.contains(3))
                || (player2.contains(4) && player2.contains(5) && player2.contains(6))
                || (player2.contains(7) && player2.contains(8) && player2.contains(9)))
        {
            winner = 2
        }

// win by full column
        if ((player1.contains(1) && player1.contains(4) && player1.contains(7))
                || (player1.contains(2) && player1.contains(5) && player1.contains(8))
                || (player1.contains(3) && player1.contains(6) && player1.contains(9)))
        {
            winner = 1
        }

        if ((player2.contains(1) && player2.contains(4) && player2.contains(7))
                || (player2.contains(2) && player2.contains(5) && player2.contains(8))
                || (player2.contains(3) && player2.contains(6) && player2.contains(9)))
        {
            winner = 2
        }

// win by diagonal
        if ((player1.contains(1) && player1.contains(5) && player1.contains(9))
                || (player1.contains(3) && player1.contains(5) && player1.contains(7)))
        {
            winner = 1
        }

        if ((player2.contains(1) && player2.contains(5) && player2.contains(9))
                || (player2.contains(3) && player2.contains(5) && player2.contains(7)))
        {
            winner = 2
        }

        if (winner != -1 )
        {
            if (winner == 1) showAlert(this,"Player 1 won!")
//                Toast.makeText(this,"Player 1 won!", Toast.LENGTH_LONG).show()
            else showAlert(this,"Player 2 won!")
//                Toast.makeText(this, "Player 2 won!", Toast.LENGTH_LONG).show()
        } else {
            if ((player1.size == 5 && player1.size == 4)) {
                showAlert(this,"Fair Game!")
//                Toast.makeText(this,"Fair Game!", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun showAlert(context: Context, msg: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = AlertDialog.Builder(context, android.R.style.Theme_Material_Dialog_Alert)
        } else {
            builder = AlertDialog.Builder(context)
        }
        builder!!.setTitle("Tic Tac Toe")
                .setMessage(msg)
                .setPositiveButton(android.R.string.yes) { dialog, which ->
                    val intent = intent
                    finish()
                    startActivity(intent)
                }
                .show()
    }

    private fun AutoPlay() {
        val emptyCells=ArrayList<Int>()
        for (cellID in 1..9){
            if(!(player1.contains(cellID) || player2.contains(cellID))){
                emptyCells.add(cellID)
            }
        }

        val r = Random()
        val randIndex = r.nextInt(emptyCells.size-0)+0
        val cellID = emptyCells[randIndex]

        var btnSelected:Button

        when(cellID){
            1-> btnSelected=btn1
            2-> btnSelected=btn2
            3-> btnSelected=btn3
            4-> btnSelected=btn4
            5-> btnSelected=btn5
            6-> btnSelected=btn6
            7-> btnSelected=btn7
            8-> btnSelected=btn8
            9-> btnSelected=btn9
            else -> btnSelected=btn1
        }

        playGame(cellID,btnSelected)
    }
}
