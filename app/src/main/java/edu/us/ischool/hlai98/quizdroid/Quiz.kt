package edu.us.ischool.hlai98.quizdroid

class Quiz {
    val question: String
    val answers: MutableList<String>
    val correct: Int
    constructor(question:String, answers: MutableList<String>, correct:Int) {
        this.question = question
        this.answers = answers
        this.correct = correct
    }
}