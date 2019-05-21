package edu.us.ischool.hlai98.quizdroid

class Topic(val title: String,val shortDescription : String,val longDescription: String, val result: MutableList<Quiz> = mutableListOf()) {
    fun add(a: Quiz) {
        result.add(a)
    }
    fun getAll(): List<Quiz> {
        return this.result.toList()
    }
}