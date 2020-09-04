package com.example.projectcellep.ui

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.projectcellep.R
import kotlinx.android.synthetic.main.activity_calculo.*
import kotlin.math.roundToLong

class CalculoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculo)
        title = this.resources.getString(R.string.titleCalculoActivty)
        actionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        settingEndIcons()
        btnCalcular()
    }

    private fun settingEndIcons() {
        textFieldPeso.setEndIconOnClickListener {
            edtPeso.setText("")
        }
        textFieldAltura.setEndIconOnClickListener {
            edtAltura.setText("")
        }
    }

    private fun btnCalcular() {
        btnCalcular.setOnClickListener {
            validateIsFieldsEmpty()
        }
    }

    private fun validateIsFieldsEmpty() {
        val altura = edtAltura.text?.trim().toString()
        val peso = edtPeso.text?.trim().toString()
        when {
            peso.isEmpty() -> {
                showDialogFun("Peso")
                textFieldPeso.requestFocus()
            }
            altura.isEmpty() -> {
                showDialogFun("Altura")
                textFieldAltura.requestFocus()
            }
            else -> {
                makeCalculation()
            }
        }
    }

    private fun makeCalculation() {
        val altura = edtAltura.text?.trim().toString().toDouble()
        val peso = edtPeso.text?.trim().toString().toDouble()
        val total = (peso / (altura * altura)).roundToLong()
        showResultOfCalculation(total)
    }

    private fun showResultOfCalculation(total: Long) {
        val type = verifyTotal(total)
        val builder = AlertDialog.Builder(this)
        builder.setTitle("O resultado do seu calculo")
        builder.setMessage("Total: $total você está $type")
        builder.setPositiveButton("Ok") { dialog, which ->
            returningDataToMain(total, type)
        }
        val dialog: AlertDialog = builder.create()
        dialog.setOnShowListener {
            dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.GREEN)
        }
        dialog.show()
    }

    private fun returningDataToMain(total: Long, type: String) {
        val returnIntent = Intent()
        returnIntent.putExtra("TOTAL", total.toString())
        returnIntent.putExtra("TYPE", type)
        setResult(Activity.RESULT_OK, returnIntent)
        finish()
    }

    private fun verifyTotal(total: Long): String {
        return when (true) {
            total < 18.5 -> "abaixo de seu peso."
            total >= 18.5 && total <= 24.9 -> "no peso ideal."
            total >= 25.0 && total <= 29.9 -> "acima de seu peso."
            total >= 30.0 && total <= 34.9 -> "Obesidade grau I."
            total > 34.9 -> "Obesidade grau II (severa)."
            else -> ""
        }
    }

    private fun showDialogFun(fieldEmpty: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(resources.getString(R.string.title))
        builder.setMessage("${resources.getString(R.string.body_dialog)} $fieldEmpty")
        builder.setPositiveButton("Ok") { dialog, which ->

        }
        val dialog: AlertDialog = builder.create()
        dialog.setOnShowListener {
            dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.RED)
        }
        dialog.show()
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

}
