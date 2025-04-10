package com.example.mmapp.AHome

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.mmapp.Counter.AppViewModelFactory
import com.example.mmapp.Counter.JsonLog
import com.example.mmapp.Counter.SharedViewModel
import com.example.mmapp.Networking.BasicNetworkingActivity
import com.example.mmapp.Networking2.TypicodeActivity
import com.example.mmapp.R
import com.example.mmapp.Savings.Savings
import com.example.mmapp.Wishes.Wishes
import com.google.gson.GsonBuilder

class HomeA:AppCompatActivity(){
    private lateinit var sharedViewModel: SharedViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        val HomeA:HomeA?=null
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val appViewModelFactory = AppViewModelFactory(application)
        sharedViewModel = ViewModelProvider(this, appViewModelFactory)[SharedViewModel::class.java]

        val openSavingsButton = findViewById<Button>(R.id.openSavingsButton)
        val openWishesButton = findViewById<Button>(R.id.openWishesButton)
        val getIpButton = findViewById<Button>(R.id.getIpButton)
        val postApiButton=findViewById<Button>(R.id.postApiButton)
        val gson = GsonBuilder().setPrettyPrinting().create()
        sharedViewModel.counterSavingsButton.observe(this) {
            if(it>0) {
                val message= JsonLog("SavingsButton pressed",it.toString().toInt(),"No Previous Activity")
                val jsonString = gson.toJson(message)
            Log.d("Tracking",jsonString)
            }
        }
        sharedViewModel.counterWishesButton.observe(this) {
            if(it>0) {
                val message= JsonLog("WishesButton pressed",it.toString().toInt(),"No Previous Activity")
                val jsonString = gson.toJson(message)
            Log.d("Tracking",jsonString)
            }
        }
        sharedViewModel.countergetIpButton.observe(this) {
            if(it>0) {
                val message= JsonLog("GetIpButton pressed",it.toString().toInt(),"No Previous Activity")
                val jsonString = gson.toJson(message)
            Log.d("Tracking",jsonString)
            }
        }

        sharedViewModel.counterpostApiButton.observe(this) {
            if(it>0) {
                val message= JsonLog("PostApiButton pressed",it.toString().toInt(),"No Previous Activity")
                val jsonString = gson.toJson(message)
            Log.d("Tracking",jsonString)
            }
        }
        sharedViewModel.counterHome.observe(this) {
            if(it>0) {
                val message= JsonLog("Home Activity Opened",it.toString().toInt(),"No Previous Activity")
                val jsonString = gson.toJson(message)
                Log.d("Tracking", jsonString)
            }
        }

        openSavingsButton.setOnClickListener {
            val intent = Intent(this, Savings::class.java)
            intent.putExtra("Previous Activity","HomeA")
            startActivity(intent)
            sharedViewModel.counterSavingsButton.value=sharedViewModel.counterSavingsButton.value?.plus(1)
        }

        openWishesButton.setOnClickListener {
            val intent=Intent(this, Wishes::class.java)
            intent.putExtra("Previous Activity","HomeA")
            startActivity(intent)
            sharedViewModel.counterWishesButton.value=sharedViewModel.counterWishesButton.value?.plus(1)
        }

        getIpButton.setOnClickListener {
            val intent=Intent(this, BasicNetworkingActivity::class.java)
            intent.putExtra("Previous Activity","HomeA")
            startActivity(intent)
            sharedViewModel.countergetIpButton.value=sharedViewModel.countergetIpButton.value?.plus(1)
        }

        postApiButton.setOnClickListener {
            val intent=Intent(this, TypicodeActivity::class.java)
            intent.putExtra("Previous Activity","HomeA")
            startActivity(intent)
            sharedViewModel.counterpostApiButton.value=sharedViewModel.counterpostApiButton.value?.plus(1)
        }


    }
    override fun onResume()
    {
        super.onResume()
        sharedViewModel.counterHome.value=sharedViewModel.counterHome.value?.plus(1)

    }
}