package edu.us.ischool.hlai98.quizdroid

import android.content.Context
import android.support.v4.app.Fragment
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import java.lang.RuntimeException

class OverviewFragment : Fragment() {
    var tid: String? = null
    var longD: String? = null
    var questions: ArrayList<String>? = null
    var title: String? = null
    private var listener: StartQuiz? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        arguments?.let {
            tid = it.getString("tid")
            title = it.getString("titleC")
            longD = it.getString("longD")
            questions = it.getStringArrayList("questions")
        }
        return inflater.inflate(R.layout.topic_overview_f, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //var sum = getString(resources.getIdentifier("t${tid}_count", "string", activity?.packageName))
        var temp = questions as ArrayList<String>
        var sum = temp.size
        val overview = view.findViewById<TextView>(R.id.textview_overview)
        //overview.text = getString(resources.getIdentifier("t${tid}_name", "string", activity?.packageName))
        overview.text = title
        val description = view.findViewById<TextView>(R.id.textview_description)
        //description.text = getString(resources.getIdentifier("t${tid}_desc", "string", activity?.packageName))
        description.text = longD
        val start = view.findViewById<Button>(R.id.btn_startQuiz)
        start.text = "Begin ${sum} questions"
        start.setOnClickListener { listener?.StartQuiz() }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is StartQuiz) {
            listener = context
        } else throw RuntimeException("$context must implement OnFragmentInteractionListener")
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface StartQuiz {
        fun StartQuiz()
    }

    companion object {
        fun newInstance(
            topicid: String,
            longD: String,
            questions: ArrayList<String>,
            titleC: String
        ): OverviewFragment {
            val args = Bundle().apply {
                putString("tid", topicid)
                putString("longD", longD)
                putStringArrayList("questions", questions)
                putString("titleC", titleC)
            }

            val fragment = OverviewFragment()
            fragment.arguments = args

            return fragment
        }
    }
}