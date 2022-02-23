package ir.homework.hw7problem2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import ir.homework.hw7problem2.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    val numOfQuestions = 3
    val questionList = mutableListOf<String>()
    val answerList = mutableListOf<Boolean>()
    val userAnswerList = MutableList(numOfQuestions){UserAnswer.NOANSWER}
    val cheatList = mutableListOf<Boolean>()

    var num = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initialize()

        binding.btnTrue.setOnClickListener{
            userAnswerList[num] = UserAnswer.TRUE
            binding.btnTrue.isEnabled = false
            binding.btnFalse.isEnabled = false
            messageAnswer(answerList[num])
        }
        binding.btnFalse.setOnClickListener{
            userAnswerList[num] = UserAnswer.FALSE
            binding.btnTrue.isEnabled = false
            binding.btnFalse.isEnabled = false
            messageAnswer(!answerList[num])
        }
        binding.btnNext.setOnClickListener{
            num++
            changePage()
        }
        binding.btnPrev.setOnClickListener{
            num--
            changePage()
        }
        binding.btnCheat.setOnClickListener{
            val intent = Intent(this, CheatActivity::class.java)
            intent.putExtra("answer", answerList[num])
            startActivity(intent)
        }
    }

    fun setQuestionsAndAnswers(){
        questionList.add(getString(R.string.question1))
        answerList.add(stringToBoolean(getString(R.string.answer1)))
        questionList.add(getString(R.string.question2))
        answerList.add(stringToBoolean(getString(R.string.answer2)))
        questionList.add(getString(R.string.question3))
        answerList.add(stringToBoolean(getString(R.string.answer3)))
    }
    fun stringToBoolean(str: String): Boolean{
        return str == "true"
    }
    fun initialize(){
        setTitle("GeoQuiz")
        setQuestionsAndAnswers()
        binding.tvQuestion.text = questionList[num]
        binding.btnPrev.isEnabled = false
    }
    fun messageAnswer(boolean: Boolean){
        if (boolean)    Toast.makeText(this, "Correct",Toast.LENGTH_LONG).show()
        else    Toast.makeText(this, "Incorrect",Toast.LENGTH_LONG).show()
    }
    fun changePage() {
        binding.tvQuestion.text = questionList[num]
        binding.btnPrev.isEnabled = num != 0
        binding.btnNext.isEnabled = num != (numOfQuestions - 1)
        binding.btnFalse.isEnabled = userAnswerList[num] == UserAnswer.NOANSWER
        binding.btnTrue.isEnabled = userAnswerList[num] == UserAnswer.NOANSWER
        binding.btnCheat.isEnabled = userAnswerList[num] == UserAnswer.NOANSWER
    }
}

enum class UserAnswer{
    NOANSWER,
    TRUE,
    FALSE
}