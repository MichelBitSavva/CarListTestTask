package com.test.automobile.activity

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.app.ActivityCompat
import com.test.automobile.R
import com.test.automobile.viewModel.SharedViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val STORAGE_PERMISSIONS = arrayOf(
        Manifest.permission.CAMERA


    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        verifyPermissions()
        val sharedViewModel: SharedViewModel by viewModels()
    }


    private fun verifyPermissions() {

        val permissionCamera =
            ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA)


        if (permissionCamera != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                this,
                STORAGE_PERMISSIONS,
                1
            )
        }


    }
}