package edu.us.ischool.hlai98.quizdroid
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView

class AnswerPage :AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.answer_view)

        intent.extras.apply{
            var display = this.getString("display")
            var displayText = findViewById<TextView>(R.id.display)
            displayText.text = display
        }


        val finish = findViewById<Button>(R.id.button)
        finish.setOnClickListener{
            val intent = Intent(this@AnswerPage, MainActivity::class.java)
            startActivity(intent)
        }
    }
}