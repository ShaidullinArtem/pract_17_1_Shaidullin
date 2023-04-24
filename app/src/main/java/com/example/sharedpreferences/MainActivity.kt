package com.example.sharedpreferences

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.View
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var pref: SharedPreferences
    private lateinit var login: EditText
    private lateinit var pass: EditText
    private lateinit var textView1: TextView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        login = findViewById(R.id.login)
        pass = findViewById(R.id.pass)
        textView1 = findViewById(R.id.textView1)
    }

    @SuppressLint("SetTextI18n")
    fun handler(view: View) {
        when (view.id) {
            R.id.save -> {
                pref = getPreferences(MODE_PRIVATE)
                val ed = pref.edit()
                ed.putString("login", login.text.toString())
                ed.putString("password", pass.text.toString())
                ed.apply()
            }
            R.id.load -> {
                pref = getPreferences(MODE_PRIVATE)
                val loginStr = pref.getString("login", "")
                val alertDialogBuilder = AlertDialog.Builder(this)
                alertDialogBuilder.setTitle("Login")
                alertDialogBuilder.setMessage("Ваш логин: $loginStr")
                alertDialogBuilder.setPositiveButton("OK") { _, _ ->
                    textView1.text = "Логин: $loginStr"
                }
                alertDialogBuilder.setNegativeButton("Отмена") { _, _ -> }
                val alertDialog = alertDialogBuilder.create()
                alertDialog.show()
            }
        }
    }

}