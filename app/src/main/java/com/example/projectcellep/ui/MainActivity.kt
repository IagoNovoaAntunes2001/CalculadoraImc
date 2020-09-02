package com.example.projectcellep.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.projectcellep.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = this.resources.getString(R.string.titleMainActivity)

        fabButton.setOnClickListener {
            startActivity(Intent(this, CalculoActivity::class.java))
        }
    }
}
