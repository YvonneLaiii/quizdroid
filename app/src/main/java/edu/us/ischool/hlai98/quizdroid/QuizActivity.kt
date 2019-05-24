package edu.us.ischool.hlai98.quizdroid

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.util.Log

class QuizActivity : AppCompatActivity(), OverviewFragment.StartQuiz, QuestionFragment.SeeAnswer, AnswerFragment.NextQuestionOrFinish {
    var chosen = ""
    var current: Int = 0
    var correct: Int = 0

    var totalNumberOfQuestion = 0
    var chosenTitle = ""
    var questions = arrayListOf<String>()
    var questionOption = arrayListOf<String>()
    var questionAnswer = arrayListOf<Int>()
    var longDescription = ""

    private val fragmentManager = supportFragmentManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.quiz)
        var fragment: Fragment? = null
        chosen = intent.extras[TID].toString()

        chosenTitle = intent.extras[TITLE].toString()
        var questionOptions = intent.extras[QUESTIONOPTIONS] as ArrayList<Array<String>>
        longDescription = intent.extras[LONGDESCRIPTION].toString()
        questions = intent.extras[QUESTIONTEXT] as ArrayList<String>
        questionAnswer = intent.extras[QUESTIONANSWER] as ArrayList<Int>

        questionOption = arrayListOf<String>()
        totalNumberOfQuestion = questions.size

        for (i in questionOptions) {
            for (j in i) {
                questionOption.add(j)
            }
        }

        //Log.i("okokook", chosen) // 1
        //Log.i("okokook", chosenTitle)
        // Log.i("okokook", chosenText[0])
        //Log.i("okokook", questionOptions[0][0])
        //Log.i("okokook", longDescription)

        if (current == 0) {
            fragment = OverviewFragment.newInstance(chosen, longDescription, questions, chosenTitle)
        }
        if (null != fragment) {
            val ft = fragmentManager.beginTransaction()
            ft.replace(R.id.frameLayout, fragment)
            ft.commit()
        }
    }

    override fun StartQuiz() {
        //Log.i("1231231231", current.toString())
        //Log.i("1231231231", chosen)

        val ft = fragmentManager.beginTransaction()
        ft.replace(
            R.id.frameLayout,
            QuestionFragment.newInstance(current, chosen, questions, questionOption, questionAnswer)
        )
        ft.commit()
        current++
    }

    override fun SeeAnswer(chose: String, answer: String) {
        if (chose == answer) {
            correct++
        }
        val ft = fragmentManager.beginTransaction()
        val fragment: Fragment = AnswerFragment.newInstance(
            chose,
            answer,
            correct.toString(),
            current.toString(),
            chosen,
            totalNumberOfQuestion
        )
        ft.replace(R.id.frameLayout, fragment)
        ft.commit()
    }

    override fun NextQuestionOrFinish() {
        var temp = questions as ArrayList<String>
        var sum = temp.size
        if (current.toString() == sum.toString()) {
            val intent = Intent(this@QuizActivity, MainActivity::class.java)
            startActivity(intent)
        } else {
            StartQuiz()
        }
    }

    companion object {
        val TID = "TID"
        val LONGDESCRIPTION = "longDesc"
        val TITLE = "title"
        val QUESTIONTEXT = "questionText"
        val QUESTIONOPTIONS = "questionOptions"
        val QUESTIONANSWER = "questionAnswer"
    }
}