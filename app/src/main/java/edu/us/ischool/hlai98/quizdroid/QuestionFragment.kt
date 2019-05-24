package edu.us.ischool.hlai98.quizdroid

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.AppCompatRadioButton
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView

class QuestionFragment:Fragment() {
    private var listener: SeeAnswer? = null
    var num : Int? = null
    var tid: String? = null
    var selected : String? = null
    var questions: ArrayList<String>? = null
    var options: ArrayList<String>? = null
    var answers: ArrayList<Int>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            num = it.getInt("current")
            tid = it.getString("tid")
            questions = it.getStringArrayList("questions")
            options = it.getStringArrayList("options")
            answers = it.getIntegerArrayList("answers")
        }
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.question_f, container, false)

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val question = view.findViewById<TextView>(R.id.textview_question)
        var temp = questions as ArrayList<String>
        var ques = temp[num.toString().toInt()]
        question.text = ques
        val tempp = options as ArrayList<String>
        //question.text = getString(resources.getIdentifier("t${tid}q${num}_question", "string", activity?.packageName))

        //val options = getString(resources.getIdentifier("t${tid}q${num}_answer", "string", activity?.packageName))

        //val result: List<String> = options.split(";").map(String::trim).shuffled()
        //var answer: String = getString(resources.getIdentifier("t${tid}q${num}_correct", "string", activity?.packageName))

        var answer = answers as ArrayList<Int>
        var number = num as Int
        var ans = answer[number] - 1
        var correctAnswer = tempp[4*num.toString().toInt()+ans]


       // for (i in 0 until number)

        Log.i("1231231", correctAnswer)
        //Log.i("1231231", num.toString())

        //var answerTemp = answer[number]

        for (i in 1 until 5) {
            val temp = view.findViewById<RadioButton>(resources.getIdentifier("option_$i", "id", activity?.packageName))
            //temp.text = (i).toString() + ") " + result[(i-1)]
            temp.text = (i-1).toString() + ") " + tempp[4*num.toString().toInt()+i-1]
        }
        val btn = view.findViewById<Button>(R.id.btn_sumbit)
        btn.isEnabled = false
        val radio = view.findViewById<RadioGroup>(R.id.answer_choices)
        radio.setOnCheckedChangeListener { _, _ ->
            btn.isEnabled = true
            val chosen = view.findViewById<AppCompatRadioButton>(view.findViewById<RadioGroup>(R.id.answer_choices).checkedRadioButtonId)
            selected = chosen.text.toString().split(") ")[1].trim()
        }
        btn.setOnClickListener {
            listener?.SeeAnswer(selected.toString(), correctAnswer)
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is SeeAnswer) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }
    interface SeeAnswer{
        fun SeeAnswer(chosen:String, answer: String)
    }
    companion object {

        fun newInstance(current: Int, tid: String, questions:ArrayList<String>, questionOptions: ArrayList<String>, questionAnswer: ArrayList<Int>) = QuestionFragment().apply {
            arguments = Bundle().apply {
                putInt("current",current)
                putString("tid",tid)
                putStringArrayList("questions", questions)
                putIntegerArrayList("answers", questionAnswer)
                putStringArrayList("options", questionOptions)
            }
        }
    }
}