package com.example.projectcellep.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.projectcellep.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val REQUESTCODE: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = this.resources.getString(R.string.titleMainActivity)

        fabButton.setOnClickListener {
            startActivityForResult(Intent(this, CalculoActivity::class.java), REQUESTCODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == REQUESTCODE && resultCode == Activity.RESULT_OK) {
            val totalReceived = data?.getStringExtra("TOTAL")
            val typeReceived = data?.getStringExtra("TYPE")

        }
    }

}
