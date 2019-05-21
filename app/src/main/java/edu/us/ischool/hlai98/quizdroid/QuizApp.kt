package edu.us.ischool.hlai98.quizdroid

import android.app.Application
import android.util.Log

class QuizApp : Application() {
    val repo = TopicRepo()

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "loaded and running")
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