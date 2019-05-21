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
    var num : String? = null
    var tid: String? = null
    var selected : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            num = it.getString("current")
            tid = it.getString("tid")
        }
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.question_f, container, false)

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val question = view.findViewById<TextView>(R.id.textview_question)
        question.text = getString(resources.getIdentifier("t${tid}q${num}_question", "string", activity?.packageName))

        val options = getString(resources.getIdentifier("t${tid}q${num}_answer", "string", activity?.packageName))

        val result: List<String> = options.split(";").map(String::trim).shuffled()
        var answer: String = getString(resources.getIdentifier("t${tid}q${num}_correct", "string", activity?.packageName))

        for (i in 1 until 5) {
            val temp = view.findViewById<RadioButton>(resources.getIdentifier("option_$i", "id", activity?.packageName))
            temp.text = (i).toString() + ") " + result[(i-1)]
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
            listener?.SeeAnswer(selected.toString(), answer)
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
        @JvmStatic
        fun newInstance(current: String, tid: String) = QuestionFragment().apply {
            arguments = Bundle().apply {
                putString("current",current)
                putString("tid",tid)
            }
        }
    }
}