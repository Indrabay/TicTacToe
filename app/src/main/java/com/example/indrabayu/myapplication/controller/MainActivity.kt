package com.example.indrabayu.myapplication.controller

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.indrabayu.myapplication.R
import com.example.indrabayu.myapplication.model.Board
import com.example.indrabayu.myapplication.model.Player
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var board : Board
    private lateinit var buttonGroup : ViewGroup
    private lateinit var player1String: String
    private lateinit var player2String: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        buttonGroup = findViewById(R.id.gridlayout) as ViewGroup
        board = Board()
        var intent : Intent = getIntent()
        player1String = (intent.extras.getSerializable("playerOneName")).toString()
        player2String = (intent.extras.getSerializable("playerTwoName")).toString()
        player1Name.setText(player1String)
        player2Name.setText(player2String)
    }

    fun markCell(view : View){
        var button : Button = view as Button
        var coordinate = button.tag.toString()
        var row = coordinate.substring(0,1)
        var col = coordinate.substring(1,2)

        var playing : Player? = board.markCellOnBoard(row.toInt(), col.toInt())
        if (playing != null) {
            button.setText(playing.toString())
            if (board.winner != null) {
                if (playing.toString().equals("X")) {
                    visible.setText(player1String)
                } else {
                    visible.setText(player2String)
                }
                winner.setText("WINNER")
            } else if (board.cellsChoosen == 9) {
                visible.setText("TOO BAD")
                winner.setText("DRAW")
            }
        } else {
            Toast.makeText(this, "sudah selesai bro!", Toast.LENGTH_SHORT).show()
        }
    }

    fun resetBoard(view : View) {
        board.restart()
        board.setCellsChoosen(0)

        visible.setText("")
        winner.setText("")
        var i = 0
        while (i < gridlayout.childCount) {
            (buttonGroup.getChildAt(i) as Button).setText("")
            i++
        }
    }
}
