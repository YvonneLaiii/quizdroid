package edu.us.ischool.hlai98.quizdroid

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView

class Question : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)
        val qNum = intent.extras[qId].toString().toInt()
        val id = intent.extras[tid].toString()
        val question = findViewById<TextView>(R.id.textview_question)
        question.text = getString(resources.getIdentifier("t${id}q${qNum}_question", "string", packageName))
        val options = getString(resources.getIdentifier("t${id}q${qNum}_answer", "string", packageName))
        val result: List<String> = options.split(";").map(String::trim).shuffled()
        var correctAnswer: String = getString(resources.getIdentifier("t${id}q${qNum}_correct", "string", packageName))
        for (i in 1 until 5) {
            val resourceBtn: RadioButton = findViewById<RadioButton>(resources.getIdentifier("option_$i", "id", packageName))
            resourceBtn.text = (i).toString() + ") " + result[(i - 1)]
        }
        val btn = findViewById<Button>(R.id.btn_sumbit)
        btn.isEnabled = false
        val radio = findViewById<RadioGroup>(R.id.answer_choices)
        radio.setOnCheckedChangeListener { _, _ ->
            btn.isEnabled = true
        }
        btn.setOnClickListener {
            val chosen = findViewById<RadioButton>(findViewById<RadioGroup>(R.id.answer_choices).checkedRadioButtonId)
            val next = Intent(this@Question, Answer::class.java)
                next.putExtra(tid, intent.extras[tid].toString())
                next.putExtra(sum, intent.extras[sum].toString())
                next.putExtra(Answer.qId, qNum.toString().toInt() + 1)
                next.putExtra(Answer.answerChosen, chosen.text.split(") ")[1].trim())
                next.putExtra(Answer.answerRight, correctAnswer.trim())
            if (chosen.text.split(") ")[1].trim() == correctAnswer) {
                next.putExtra(right, intent.extras[right].toString().toInt() + 1)
            } else {
                next.putExtra(right, intent.extras[right].toString().toInt())
            }
            startActivity(next)
        }
    }
    companion object {
        var topics = "topicname"
        var tid = "topicid"
        var qId = "qid"
        var right = "numright"
        var sum = "numtotal"
    }
}