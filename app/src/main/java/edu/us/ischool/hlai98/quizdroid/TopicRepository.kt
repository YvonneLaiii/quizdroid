package edu.us.ischool.hlai98.quizdroid

class TopicRepo : TopicRepository {
    private var topic = HashMap<String, Topic>()
    override fun getTopic(name: String): Topic {
        return this.topic[name]!!
    }
    override fun getTopics(): MutableList<Topic> {
        return this.topic.values.toMutableList()
    }
    init {
        this.topic["Math"] = Topic("Math", "This is Math questions.","The following questions will be simple addition.")
        this.topic["Math"]?.add(Quiz("1+1=", mutableListOf("1","2","3","4"), 1))
        this.topic["Math"]?.add(Quiz("1+2=", mutableListOf("3","4","5","6"), 0))
        this.topic["Math"]?.add(Quiz("1+3=", mutableListOf("3","4","5","6"), 1))
        this.topic["Math"]?.add(Quiz("1+4=", mutableListOf("2","3","4","5"), 3))
        this.topic["Physics"] = Topic("Physics", "This is Physics questions.","Test your physics knowledge.")
        this.topic["Physics"]?.add(Quiz("what is Newton First Law", mutableListOf("IDK","No answer","You tell me","Law of Inertia"), 3))
        this.topic["Physics"]?.add(Quiz("What is volocity?", mutableListOf("IDK","You tell me","speed","speed with direction"), 3))
        this.topic["Marvel Super Heroes"] = Topic("Marvel Super Heroes", "This is Marvel's questions.","Questions about the Avenger : End Game (Spoiler Alert)")
        this.topic["Marvel Super Heroes"]?.add(Quiz("Who died in the movie?", mutableListOf("Iron Man", "NOOOOOO!","IDK","I refuse to answer."), 0))
        this.topic["Marvel Super Heroes"]?.add(Quiz("Who is my favorite hero?", mutableListOf("No one", "Thor","Spider man","Iron Man"), 1))
        this.topic["Marvel Super Heroes"]?.add(Quiz("Loki is the brother of whom?", mutableListOf("Thor","Spider Man","Tony Stark","Stan Lee"), 0))
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
    val answers: MutableList<String>
    val correct: Int
    constructor(question:String, answers: MutableList<String>, correct:Int) {
        this.question = question
        this.answers = answers
        this.correct = correct
    }
}