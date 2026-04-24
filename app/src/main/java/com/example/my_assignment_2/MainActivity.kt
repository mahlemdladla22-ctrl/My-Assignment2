package com.example.my_assignment_2

import android.os.Bundle
import android.view.TextureView
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.my_assignment_2.ui.theme.My_Assignment_2Theme

class MainActivity : ComponentActivity() {

    //Simple Arrays

    private val questions = arrayOf(
        "Blinking rapidly for one minute straight can help you fall asleep fast",
        "Placing a wooden spoon across the top of a boiling pot of pasta stops the water from boiling over",
        "Putting toothpaste on a glass cracked screen fixes it by removing the cracks",
        "Toothpaste removes pimples",
        "To peel garlic in seconds you put a whole garlic in a jar, screw on the lid and shake vigorously",
        "Putting batteries in the fridge extends battery life",
        "Peeing on a jellyfish sting helps",
        "Placing a damp paper towel over food when reheating prevents the food from drying out",
        "If you rub a cotton swab dipped in lemon juice over highlighted text the ink will fade and disappear",
        "If you wrap yourself in plastic and exercise, you will sweat off the fat"
    )

    private val answers = arrayOf(
        true,true,false,false,true,false,false,true,true,false
    )
    private var currentQuestion = 0
    private var score = 0
    private val feedbackList = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        showWelcomeScreen()
    }

    private fun showWelcomeScreen() {
        setContentView(R.layout.activity_main)
        val startButton = findViewById<Button>(R.id.btnStart)
        startButton.setOnClickListener {
            currentQuestion = 0
            score = 0
            feedbackList.clear()
            showQuestionScreen()
        }
    }

    private fun showQuestionScreen() {
        setContentView(R.layout.activity_main2)

        val txtQuiz = findViewById<TextView>(R.id.txtQuiz)
        val txtQuestion = findViewById<TextView>(R.id.txtQuestion)
        val btnHack = findViewById<Button>(R.id.btnHack)
        val btnMyth = findViewById<Button>(R.id.btnMyth)
        val txtResults = findViewById<TextView>(R.id.txtResults)
        val btnNext = findViewById<Button>(R.id.btnNext)

        txtQuestion.text = questions[currentQuestion]
        txtResults.text = ""
        var answered = false

        btnHack.setOnClickListener {
            if (!answered) {
                checkAnswer(true, txtResults)
                answered = true
            }
        }

        btnMyth.setOnClickListener {
            if (!answered) {
                checkAnswer(false, txtResults)
                answered = true
            }
        }

        btnNext.setOnClickListener {
            currentQuestion++
            if (currentQuestion < questions.size) {
                showQuestionScreen()
            } else {
                showScoreScreen()
            }
        }
    }

    private fun checkAnswer(userAnswer: Boolean, feedbackText: TextView) {
        val correct = answers[currentQuestion]
        if (userAnswer == correct) {
            feedbackText.text = "Correct! Master Hacker"
            score++
            feedbackList.add("Q${currentQuestion + 1}: ")
        } else {
            feedbackText.text = "Wrong! Better luck next time"
            feedbackList.add("Q${currentQuestion + 1}: ")
        }
    }

    private fun showScoreScreen() {
        setContentView(R.layout.activity_main3)

        val txtScore = findViewById<TextView>(R.id.txtScore)
        val txtYourScore = findViewById<TextView>(R.id.txtYourScore)
        val txtFeedback = findViewById<TextView>(R.id.txtFeedback)
        val btnReview = findViewById<Button>(R.id.btnReview)
        val btnExit = findViewById<Button>(R.id.btnExit)

        txtYourScore.text = "You scored $score out of ${questions.size}"
        txtFeedback.text = if (score >= 5) "Well Done! You are a Master Hacker" else "Cool! Almost halfway, Be safe online"

        btnReview.setOnClickListener {
            val facts = questions.mapIndexed { index, q ->
                "${index + 1}. $q\nAnswer: ${answers[index]}"
            }.joinToString("\n\n")
            Toast.makeText(this, facts, Toast.LENGTH_LONG).show()
        }

        btnExit.setOnClickListener {
            finish()
        }
    }
}