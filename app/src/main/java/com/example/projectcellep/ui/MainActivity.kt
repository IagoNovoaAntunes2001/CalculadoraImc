package com.example.projectcellep.ui

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.projectcellep.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var type: String? = ""
    private var total: Long = 0
    private lateinit var sharedPref: SharedPreferences
    private val REQUESTCODE: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = this.resources.getString(R.string.titleMainActivity)

        sharedPref = this@MainActivity.getSharedPreferences(
            getString(R.string.preference_file_key), Context.MODE_PRIVATE
        )

        total = sharedPref.getLong("TOTAL", 0)
        type = sharedPref.getString("TYPE", "")

        getDataFromShared()
        fabButton.setOnClickListener {
            startActivityForResult(Intent(this, CalculoActivity::class.java), REQUESTCODE)
        }
    }

    private fun getDataFromShared() {
        if (total.toInt() == 0 && type == "") {
            setDefaultTexts()
        } else {
            setValuesFromShared(total, type)
        }
    }

    private fun setValuesFromShared(total: Long, type: String?) {
        arrowIcon.visibility = View.GONE
        cardView.visibility = View.VISIBLE
        textMessage.text = ""
        textCliqueAqui.text = ""
        textTitleTot.text = resources.getString(R.string.title_total)
        textTotal.text = total.toString()
        textType.text = type
    }

    private fun setDefaultTexts() {
        textMessage.text = resources.getString(R.string.mainActivity_default_text)
        cardView.visibility = View.GONE
        arrowIcon.visibility = View.VISIBLE
        textCliqueAqui.text = resources.getString(R.string.mainActivity_default_message)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUESTCODE && resultCode == Activity.RESULT_OK) {
            storingTheDataReceived(data)
        }

    }

    private fun storingTheDataReceived(data: Intent?) {
        val totalReceived = data?.getStringExtra("TOTAL")
        val typeReceived = data?.getStringExtra("TYPE")

        sharedPref = this@MainActivity.getSharedPreferences(
            getString(R.string.preference_file_key), Context.MODE_PRIVATE
        )
        sharedPref.edit().putLong("TOTAL", totalReceived!!.toLong()).apply()
        sharedPref.edit().putString("TYPE", typeReceived).apply()
        setValuesFromShared(totalReceived.toLong(), typeReceived)
    }
}

