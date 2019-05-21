package edu.us.ischool.hlai98.quizdroid

import android.content.Context
import android.support.v4.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import java.lang.RuntimeException

class OverviewFragment : Fragment() {
    var tid : String? = null
    private var listener: StartQuiz? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            tid = it.getString("tid")
        }
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.topic_overview_f, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //println(tid)
        var sum = getString(resources.getIdentifier("t${tid}_count", "string", activity?.packageName))
        val overview = view.findViewById<TextView>(R.id.textview_overview)
        overview.text = getString(resources.getIdentifier("t${tid}_name", "string", activity?.packageName))
        val description = view.findViewById<TextView>(R.id.textview_description)
        description.text = getString(resources.getIdentifier("t${tid}_desc", "string", activity?.packageName))
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
    interface  StartQuiz {
        fun StartQuiz()
    }
    companion object {
        @JvmStatic
        fun newInstance(topicid: String) = OverviewFragment().apply {
            arguments = Bundle().apply { putString("tid", topicid) }
        }
    }
}