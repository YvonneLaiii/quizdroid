package edu.us.ischool.hlai98.quizdroid

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import org.json.JSONObject
import java.io.IOException
import org.json.JSONArray
import java.util.*

class QuizApp : Application() {

    var temp = JSONArray()

    var repo = TopicRepo(temp)

    private lateinit var sharedPreferences: SharedPreferences
    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "loaded and running")
        Log.i("quizapp", "1")
        readWriteJson()
        Log.i("quizapp", "2")
       // temp = JSONArray(readJSONFromAsset())
        Log.i("quizapp", "3")
        temp = JSONArray(readJSONFromAsset())
        repo = TopicRepo(temp)
        //JSONArray
    }


    fun readWriteJson() {
        sharedPreferences = getSharedPreferences("USER_PREFERENCES_KEY", Context.MODE_PRIVATE)

        // Get current time stamp from SharedPreferences & print it
        val defaultErrorValue = -1L
        val timestamp = sharedPreferences.getLong("timestamp", defaultErrorValue)

        // Updated timestamp in SharedPreferences & print it
        sharedPreferences
            .edit()
            .putLong("timestamp", System.currentTimeMillis() + 1000)
            .apply()

    }

    fun readJSONFromAsset(): String? {
        val inputStream = assets.open("questions.json")
        Log.i("okk", "okokokoko")
        val size = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()

        var json: String? = String(buffer, Charsets.UTF_8)

        return json
    }

    companion object {
        const val TAG = "QuizApp"
        private var single: QuizApp? = null
        fun getSingleton(): QuizApp {
            return single!!
        }
    }
    init {
        single = this
    }
}