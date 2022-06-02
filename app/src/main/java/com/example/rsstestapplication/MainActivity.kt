package com.example.rsstestapplication

import android.content.ContentValues
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class MainActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(ContentValues.TAG, "Запуск MainActvivty")
        setContentView(R.layout.activity_container)

    }
}