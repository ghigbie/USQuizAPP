package com.trainingtrack.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        val question: Question = Question(R.string.question_declaration, answerTrue = true)

        false_button.setOnClickListener{
            Toast.makeText(this, "False", Toast.LENGTH_SHORT).show()
        }

        true_button.setOnClickListener {
            Toast.makeText(this, "True", Toast.LENGTH_SHORT).show()
        }

        question

    }




}
