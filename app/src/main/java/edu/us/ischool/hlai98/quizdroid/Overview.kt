package edu.us.ischool.hlai98.quizdroid

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView

class Overview : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_overview)
        val id = intent.extras[tid].toString()
        val overview = findViewById<TextView>(R.id.textview_overview)
        overview.text = getString(resources.getIdentifier("t${id}_name", "string", packageName))
        val description = findViewById<TextView>(R.id.textview_description)
        description.text = getString(resources.getIdentifier("t${id}_desc", "string", packageName))
        val total = getString(resources.getIdentifier("t${id}_count", "string", packageName))
        val num = findViewById<TextView>(R.id.question_number)
        num.text = "$total questions"
        val start = findViewById<Button>(R.id.btn_startQuiz)
        start.setOnClickListener {
            val intent = Intent(this@Overview, Question::class.java)
            intent.putExtra(Question.tid, id)
            intent.putExtra(Question.qId, "0")
            intent.putExtra(Question.right, "0")
            intent.putExtra(Question.sum, total)
            startActivity(intent)
        }
    }
    companion object {
        var topics = "topicname"
        var tid = "topicid"
        var description = "topicdesc"
        var sum = "numtotal"
        var right = "numright"
    }
}


