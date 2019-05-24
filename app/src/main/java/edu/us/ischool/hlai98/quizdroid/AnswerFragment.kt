package edu.us.ischool.hlai98.quizdroid

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import java.lang.RuntimeException

class AnswerFragment: Fragment() {
    var correctAnswer: String? = ""
    var selectedAnswer: String? = ""
    var sum: String? = ""
    var currentNum: String? = ""
    var tid: String? = ""
    var totalNumber: Int? = 0
    private var listener: NextQuestionOrFinish? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.arguments?.let {
            correctAnswer = it.getString("correctAnswer")
            selectedAnswer = it.getString("selectedAnswer")
            sum = it.getString("sum")
            currentNum = it.getString("current")
            tid = it.getString("tid")
            totalNumber = it.getInt("totalNumber")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.answer_f, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var all: String = getString(resources.getIdentifier("t${tid}_count", "string", activity?.packageName))
        val Abtn = view.findViewById<Button>(R.id.button)
        val user = view.findViewById<TextView>(R.id.textview_chosen)
        val correctA = view.findViewById<TextView>(R.id.textview_correct)
        val oneSum = view.findViewById<TextView>(R.id.textview_sum)
        val allSum = view.findViewById<TextView>(R.id.textview_summary)
        if (correctAnswer.toString() == selectedAnswer.toString()) {
            oneSum.text = "You got it right!"
        } else {
            oneSum.text = "You got it wrong!"
        }
        user.text = "Your answer: " + selectedAnswer.toString()
        correctA.text = "Correct answer: " + correctAnswer.toString()

        allSum.text = "You have " + sum + " out of " + totalNumber + " correct"
        var number = totalNumber as Int
        if (currentNum == (number).toString()) {
            Abtn.text = "Finish"
        } else {
            Abtn.text = "Next"
        }

        Abtn.setOnClickListener {
            listener?.NextQuestionOrFinish()
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is NextQuestionOrFinish) {
            listener = context
        } else throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface NextQuestionOrFinish {
        fun NextQuestionOrFinish()
    }

    companion object {
        @JvmStatic
        fun newInstance(chose: String, answer: String, correct: String, current: String, tid: String, total: Int) =
            AnswerFragment().apply {
                arguments = Bundle().apply {
                    putString("correctAnswer", answer)
                    putString("selectedAnswer", chose)
                    putString("sum", correct)
                    putString("current", current)
                    putString("tid", tid)
                    putInt("totalNumber", total)
                }
            }
    }
}