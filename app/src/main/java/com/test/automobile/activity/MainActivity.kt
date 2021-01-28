package com.test.automobile.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.test.automobile.R

class MainActivity : AppCompatActivity() {
    private var TAG = "MAIN_ACTIVITY"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}