package edu.us.ischool.hlai98.quizdroid

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView

class TopicOverview : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.topic_overview)
        val descriptions = mapOf(
            "Math" to "\nTotal number of question: 1. " +
                    "\nThe question will be a simple addtion question.",
            "Physics" to "\nTotal number of question: 1. " +
                    "\nThe question will be about Newton's law.",
            "Marvel Super Heroes" to "\nTotal number of question: 1. " +
                    "\nThe question will be about Marvel's newest movie." +
                    "\n(!!! Spoiler alert !!!)")
        var chosen = ""
        intent.extras.apply{
            var topicName = this.getString("topic")
            chosen = topicName
            var description = findViewById<TextView>(R.id.description)
            description.text = "Topic: " + topicName!! + descriptions.get(topicName)
        }
        val begin_btn = findViewById<Button>(R.id.begin_btn)
        begin_btn.setOnClickListener{
            val intent = Intent(this@TopicOverview, QuestionPage::class.java)
            val topic: String = this.toString()
            intent.putExtra("topic", chosen)
            startActivity(intent)
        }
    }
}