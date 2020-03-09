package com.trainingtrack.myapplication

import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity()  {

    private lateinit var questionBank: ArrayList<Question>
    private var score: Int = 0
    private var currentQuestionIndex: Int = 0

    private fun checkAnswer(question: Question, answerGiven: Boolean){
       if(question.answerTrue == answerGiven){
           score++
           Toast.makeText(this, R.string.correct_answer, Toast.LENGTH_SHORT).show()
       }else{
           Toast.makeText(this, R.string.wrong_answer, Toast.LENGTH_SHORT).show()
       }
        currentQuestionIndex = (currentQuestionIndex + 1) % questionBank.size
        if(questionBank.size >= currentQuestionIndex + 1){
            setQuestionText(answer_text_view, currentQuestionIndex)
            setScoreText(score_text, score)
        }else{
            play_again_button.visibility = View.VISIBLE
            false_button.visibility = View.GONE
            true_button.visibility = View.GONE
        }
    }

    private fun setQuestionText(textView: TextView, questionIndex: Int){
        textView.setText(questionBank[questionIndex].answerResId)
    }

    private fun setScoreText(textView: TextView, score: Int){
        textView.setText("Score: $score")
    }

    private fun resetViews(){
        false_button.visibility = View.VISIBLE
        true_button.visibility = View.VISIBLE
        play_again_button.visibility = View.GONE
        setScoreText(score_text, 0)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        resetViews()

        questionBank = arrayListOf<Question>(
            Question(R.string.question_amendments, answerTrue = false),
            Question(R.string.question_constitution, answerTrue = true),
            Question(R.string.question_declaration, answerTrue = true),
            Question(R.string.question_independence_rights, answerTrue = true),
            Question(R.string.question_religion, answerTrue = true),
            Question(R.string.question_government, answerTrue = false),
            Question(R.string.question_government_feds, answerTrue = false),
            Question(R.string.question_government_senators, answerTrue = true)
        )

        answer_text_view.setText(questionBank[currentQuestionIndex].answerResId)

        false_button.setOnClickListener{
            if(questionBank.size >= currentQuestionIndex + 1) {
                Toast.makeText(this, "False", Toast.LENGTH_SHORT).show()
                checkAnswer(questionBank[currentQuestionIndex], false)
            }
        }

        true_button.setOnClickListener {
            if(questionBank.size >= currentQuestionIndex + 1) {
                Toast.makeText(this, "True", Toast.LENGTH_SHORT).show()
                checkAnswer(questionBank[currentQuestionIndex], true)
            }
        }

        play_again_button.setOnClickListener {
            score = 0
            currentQuestionIndex = 0
            setQuestionText(answer_text_view, currentQuestionIndex)
            resetViews()
        }

    }




}
