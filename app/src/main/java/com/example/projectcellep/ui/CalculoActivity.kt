package com.example.projectcellep.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.projectcellep.R

class CalculoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculo)
        title = this.resources.getString(R.string.titleCalculoActivty)
    }
}
