package edu.us.ischool.hlai98.quizdroid

import android.app.PendingIntent.getActivity
import android.content.SharedPreferences
import org.json.JSONObject
import java.io.IOException
import org.json.JSONArray
import java.io.InputStream
import android.app.Application
import android.util.Log

class TopicRepo(input: JSONArray) : TopicRepository {
    //var inputFile = input

    private var topic = HashMap<String, Topic>()

    override fun getTopic(name: String): Topic {
        return topic[name]!!
    }
    override fun getTopics(): MutableList<Topic> {
        return topic.values.toMutableList()
    }

    //fun json() : HashMap<String,Topic> {
     //   Log.i("quizapp", "4")
      //  var topicJson = HashMap<String, Topic>()
      //  for (i in 0 until inputFile.length()) {
      //      val jsonObject = inputFile.get(i) as JSONObject
      //      val title = jsonObject.get("title") as String

        //    Log.i("topicR", title)

         //   val desc = jsonObject.get("desc") as String
          //  topicJson[title] = Topic(title, "This is $title", desc)
          //  val questions = jsonObject.get("questions") as JSONArray
          //  for (j in 0 until questions.length()) {
          //      val quiz = questions.get(j) as JSONObject
          //      val question = quiz.get("text") as String
          //      var answer = quiz.get("answer")
          //      answer = answer.toString().toInt()
          //      val options = quiz.get("answers") as JSONArray
          //      val one = options.get(0) as String
          //      val two = options.get(1) as String
          //      val three = options.get(2) as String
          //      val four = options.get(3) as String
          //      topicJson[title]?.add(Quiz(question, arrayOf(one,two,three,four), answer))
          //  }
        //}
        //return topicJson
    //}

    init {
       this.topic["Math"] = Topic("Math", "This is Math questions.","The following questions will be simple addition.")
       this.topic["Math"]?.add(Quiz("1+1=", arrayOf("1","2","3","4"), 2))
       this.topic["Math"]?.add(Quiz("1+2=", arrayOf("3","4","5","6"), 1))
       this.topic["Math"]?.add(Quiz("1+3=", arrayOf("3","4","5","6"), 2))
       this.topic["Math"]?.add(Quiz("1+4=", arrayOf("2","3","4","5"), 4))
       this.topic["Physics"] = Topic("Physics", "This is Physics questions.","Test your physics knowledge.")
       this.topic["Physics"]?.add(Quiz("what is Newton First Law", arrayOf("IDK","No answer","You tell me","Law of Inertia"), 4))
       this.topic["Physics"]?.add(Quiz("What is volocity?", arrayOf("IDK","You tell me","speed","speed with direction"), 4))
       this.topic["Marvel Super Heroes"] = Topic("Marvel Super Heroes", "This is Marvel's questions.","Questions about the Avenger : End Game (Spoiler Alert)")
       this.topic["Marvel Super Heroes"]?.add(Quiz("Who died in the movie?", arrayOf("Iron Man", "NOOOOOO!","IDK","I refuse to answer."), 1))
       this.topic["Marvel Super Heroes"]?.add(Quiz("Who is my favorite hero?", arrayOf("No one", "Thor","Spider man","Iron Man"), 2))
       this.topic["Marvel Super Heroes"]?.add(Quiz("Loki is the brother of whom?", arrayOf("Thor","Spider Man","Tony Stark","Stan Lee"), 1))
    }
}
interface TopicRepository {
    fun getTopic(name: String): Topic
    fun getTopics(): MutableList<Topic>
}

class Topic(val title: String,val shortDescription : String,val longDescription: String, val result: MutableList<Quiz> = mutableListOf()) {
    fun add(a: Quiz) {
        result.add(a)
    }
    fun getAll(): List<Quiz> {
        return this.result.toList()
    }
}
class Quiz {
    val question: String
    val answers: Array<String>
    val correct: Int

    constructor(question: String, answers: Array<String>, correct: Int) {
        this.question = question
        this.answers = answers
        this.correct = correct
    }
}