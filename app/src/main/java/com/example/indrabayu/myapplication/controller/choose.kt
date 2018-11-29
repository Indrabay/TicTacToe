package com.example.indrabayu.myapplication.controller

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import com.example.indrabayu.myapplication.R
import com.example.indrabayu.myapplication.controller.retrofit.RetrofitClientInstance
import com.example.indrabayu.myapplication.controller.retrofit.User
import com.example.indrabayu.myapplication.controller.retrofit.UserService
import kotlinx.android.synthetic.main.activity_choose.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class choose : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose)

        var service : UserService = RetrofitClientInstance.getRetrofitInstance().create(UserService :: class.java)
        var call : Call<List<User>> = service.allUsers

        call.enqueue(object : Callback<List<User>>{
            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                intializePlayer(response.body())
            }

            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                Toast.makeText(this@choose, "Try Again later", Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun intializePlayer(users : List<User>?) {
        var list : ArrayList<String> = ArrayList<String>()

        users?.forEach {
            list.add(it.name)
        }

        var dataAdapter : ArrayAdapter<String> = ArrayAdapter<String> (
                this@choose, android.R.layout.simple_spinner_item, list
        )

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        player1.adapter = dataAdapter
        player2.adapter = dataAdapter
    }

    fun toGame(view : View) {
        var intent = Intent(this, MainActivity :: class.java)


        var player1 : Spinner = findViewById(R.id.player1)
        var player1Name = player1.selectedItem.toString()
        var player2 : Spinner = findViewById(R.id.player2)
        var player2Name = player2.selectedItem.toString()


        intent.putExtra("playerOneName", player1Name)
        intent.putExtra("playerTwoName", player2Name)

        startActivity(intent)
    }
}
