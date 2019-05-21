package edu.us.ischool.hlai98.quizdroid

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity

class QuizActivity : AppCompatActivity(), OverviewFragment.StartQuiz, QuestionFragment.SeeAnswer, AnswerFragment.NextQuestionOrFinish {
    var chosen = ""
    var current : Int = 0
    var correct :Int = 0
    private val fragmentManager = supportFragmentManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.quiz)
        var fragment: Fragment? = null
        chosen = intent.extras[TID].toString()
        if (current == 0) {
            fragment = OverviewFragment.newInstance(chosen)
        }
        if (null != fragment) {
            val ft = fragmentManager.beginTransaction()
            ft.replace(R.id.frameLayout, fragment)
            ft.commit()
        }
    }
    override fun StartQuiz() {
        val ft = fragmentManager.beginTransaction()
        ft.replace(R.id.frameLayout, QuestionFragment.newInstance(current.toString(), chosen))
        ft.commit()
        current++
    }
    override fun SeeAnswer(chose: String, answer: String) {
        if (chose == answer) {
            correct++
        }
        val ft = fragmentManager.beginTransaction()
        val fragment: Fragment = AnswerFragment.newInstance(chose, answer, correct.toString(), current.toString(), chosen)
        ft.replace(R.id.frameLayout, fragment)
        ft.commit()
    }
    override fun NextQuestionOrFinish() {
        if (current.toString() == getString(resources.getIdentifier("t${chosen}_count","string",packageName))) {
            val intent = Intent(this@QuizActivity,MainActivity::class.java)
            startActivity(intent)
        } else {
            StartQuiz()
        }
    }
    companion object {
        val TID = "TID"
    }
}