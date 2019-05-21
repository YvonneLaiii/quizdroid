package edu.us.ischool.hlai98.quizdroid

interface myTopicR {
    fun getTopic(name: String): Topic
    fun getTopics(): MutableList<Topic>
}