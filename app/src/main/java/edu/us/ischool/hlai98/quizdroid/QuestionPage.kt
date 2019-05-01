package edu.us.ischool.hlai98.quizdroid

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import java.io.Serializable


class QuestionPage: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.question_view)

        val submit = findViewById<Button>(R.id.submit)
        val questions = mapOf<String, Question>(
            "Math" to Question(
                text = "1+1=?",
                answer = "2",
                options= arrayOf(
                    "0",
                    "1",
                    "2",
                    "3"
                )
            ),
            "Physics" to Question(
                text = "Newton's first law states that: ",
                answer = "an object will remain at rest or in uniform motion in a straight line unless acted upon by an external force.",
                options= arrayOf(
                    "Every one is equal.",
                    "Students should go to class.",
                    "an object will remain at rest or in uniform motion in a straight line unless acted upon by an external force.",
                    "F = m*a"
                )
            ),
            "Marvel Super Heroes" to Question(
                text = "Who died in the newest Avengers movie?",
                answer = "Iron man.",
                options= arrayOf(
                    "I refuse to answer!",
                    "Spider man.",
                    "Iron man.",
                    "Cap."
                )
            )
        )

        var answer : String? = ""
        var choose = ""
        intent.extras.apply {
            var chosen = this.getString("topic")
            choose = chosen
            var question = findViewById<TextView>(R.id.question)
            question.text = questions.get(chosen)?.text
            answer = questions.get(chosen)?.answer
            var radios = findViewById<RadioGroup>(R.id.radioGroup)
            for (i in 0 until radios.childCount) {
                (radios.getChildAt(i) as RadioButton).text = questions.get(chosen)!!.options[i]
            }
        }

        var radios = findViewById<RadioGroup>(R.id.radioGroup)

       /* if(rg1.getCheckedRadioButtonId()!=-1){
            int id= rg1.getCheckedRadioButtonId();
            View radioButton = rg1.findViewById(id);
            int radioId = radioGroup.indexOfChild(radioButton);
            RadioButton btn = (RadioButton) rg1.getChildAt(radioId);
            String selection = (String) btn.getText();
        }*/
      //
        //val debug = findViewById<TextView>(R.id.debug)

        //debug.text = "123" //+ checked.toString()

        submit.setOnClickListener{
            var checked = radios.getCheckedRadioButtonId()
            if(checked != -1){
                var answered = ""
                //var answered = (radios.getChildAt(checked) as RadioButton).text.toString()
                var correct = 0
                if (answer == answered) {
                    correct += 1
                }
              //  var list = questions.get(choose)
                var pass = "Your answer: " + answered + "\n" +
                        "Correct answer: " + answer + "\n" +
                        "You have " + correct + " out of 1 correct!"
                val intent = Intent(this@QuestionPage, AnswerPage::class.java)
                intent.putExtra("display", pass)

                startActivity(intent)
            }
        }

    }
}
class Question(val text: String, val answer: String, val options: Array<String>): Serializable
