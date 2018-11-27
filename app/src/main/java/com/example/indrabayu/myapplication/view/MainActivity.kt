package com.example.indrabayu.myapplication.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.indrabayu.myapplication.R
import com.example.indrabayu.myapplication.model.Board

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val board = Board()
    }
}
