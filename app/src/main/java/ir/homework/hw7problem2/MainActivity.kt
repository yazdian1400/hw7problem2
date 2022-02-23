package ir.homework.hw7problem2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ir.homework.hw7problem2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    val questionList = mutableListOf<String>()
    val answerList = mutableListOf<Boolean>()
    val userAnswerList = mutableListOf<UserAnswer>()
    val cheatList = mutableListOf<Boolean>()
    var numOfQuestion = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setTitle("GeoQuiz")
        setQuestionsAndAnswers()
        binding.tvQuestion.text = questionList[numOfQuestion]
        binding.btnPrev.isClickable = false
    }

    fun setQuestionsAndAnswers(){
        questionList.add(getString(R.string.question1))
        answerList.add(stringToBoolean(getString(R.string.answer1)))
        questionList.add(getString(R.string.question2))
        answerList.add(stringToBoolean(getString(R.string.answer2)))

    }
    fun stringToBoolean(str: String): Boolean{
        return str == "true"
    }
}

enum class UserAnswer{
    NOANSWER,
    TRUE,
    FALSE
}