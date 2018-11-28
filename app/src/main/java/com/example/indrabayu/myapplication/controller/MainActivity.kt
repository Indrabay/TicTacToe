package com.example.indrabayu.myapplication.controller

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.example.indrabayu.myapplication.R
import com.example.indrabayu.myapplication.model.Board
import com.example.indrabayu.myapplication.model.Player
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var board : Board
    private lateinit var buttonGroup : ViewGroup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        buttonGroup = findViewById(R.id.gridlayout) as ViewGroup
        board = Board()
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
                visible.setText(playing.toString())
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
