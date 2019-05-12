package edu.us.ischool.hlai98.quizdroid

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView

class Answer : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_answer)
        var Abtn = findViewById<Button>(R.id.button)
        var user = findViewById<TextView>(R.id.textview_chosen)
        var correctA = findViewById<TextView>(R.id.textview_correct)
        var oneSum = findViewById<TextView>(R.id.textview_sum)
        var allSum = findViewById<TextView>(R.id.textview_summary)
        if (intent.extras[answerChosen].toString() == intent.extras[answerRight].toString()) {
            oneSum.text = "You got it right!"
        } else {
            oneSum.text = "You got it wrong!"
        }
        user.text = "Your answer: " + intent.extras[answerChosen].toString()
        correctA.text = "Correct answer: " + intent.extras[answerRight].toString()

        var lastOne = false
        if (intent.extras[qId].toString().toInt() >= intent.extras[sum].toString().toInt()) {
            Abtn.text = "Finish"
            lastOne = true
        }
        allSum.text = "You have " + intent.extras[right] + " out of " + intent.extras[sum] + " correct"
        Abtn.setOnClickListener {
            startActivity(
                when(lastOne) {
                    true -> Intent(this@Answer, MainActivity::class.java)
                    else -> Intent(this@Answer, Question::class.java)
                        .putExtra(Question.tid, intent.extras[tid].toString())
                        .putExtra(Question.qId, intent.extras[qId].toString())
                        .putExtra(Question.right, intent.extras[right].toString())
                        .putExtra(Question.sum, intent.extras[sum].toString())
                }
            )
        }
    }
    companion object {
        var tid = "topicid"
        var qId = "qid"
        var right = "numright"
        var sum = "numtotal"
        var answerChosen = "achosen"
        var answerRight = "aright"
    }
}