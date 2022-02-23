package ir.homework.hw7problem2

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import ir.homework.hw7problem2.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    val numOfQuestions = 3
    val questionList = mutableListOf<String>()
    val answerList = mutableListOf<Boolean>()
    val userAnswerList = MutableList(numOfQuestions){UserAnswer.NOANSWER}
    val cheatList = mutableListOf<Boolean>()
    var num = 0     //÷



    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initialize()

        binding.btnTrue.setOnClickListener{
            userAnswerList[num] = UserAnswer.TRUE
            binding.btnTrue.isEnabled = false
            binding.btnTrue.setBackgroundColor(getColor(R.color.pink_dark_A100))
            binding.btnFalse.isEnabled = false
            binding.btnFalse.setBackgroundColor(getColor(R.color.blue_dark_A100))
            binding.btnCheat.isEnabled = false
            binding.btnCheat.setBackgroundColor(getColor(R.color.red_dark_A100))
            messageAnswer(answerList[num])
        }
        binding.btnFalse.setOnClickListener{
            userAnswerList[num] = UserAnswer.FALSE
            binding.btnTrue.isEnabled = false
            binding.btnTrue.setBackgroundColor(getColor(R.color.pink_dark_A100))
            binding.btnFalse.isEnabled = false
            binding.btnFalse.setBackgroundColor(getColor(R.color.blue_dark_A100))
            binding.btnCheat.isEnabled = false
            binding.btnCheat.setBackgroundColor(getColor(R.color.red_dark_A100))
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
        changePage()
    }
    fun messageAnswer(boolean: Boolean){
        if (boolean)    Toast.makeText(this, "Correct",Toast.LENGTH_LONG).show()
        else    Toast.makeText(this, "Incorrect",Toast.LENGTH_LONG).show()
    }
    @RequiresApi(Build.VERSION_CODES.M)
    fun changePage() {
        binding.tvQuestion.text = questionList[num]
        binding.btnPrev.isEnabled = num != 0
        binding.btnNext.isEnabled = num != (numOfQuestions - 1)
        binding.btnFalse.isEnabled = userAnswerList[num] == UserAnswer.NOANSWER
        binding.btnTrue.isEnabled = userAnswerList[num] == UserAnswer.NOANSWER
        binding.btnCheat.isEnabled = userAnswerList[num] == UserAnswer.NOANSWER
        if (num != 0)   binding.btnPrev.setBackgroundColor(getColor(R.color.green_A100))
        else    binding.btnPrev.setBackgroundColor(getColor(R.color.green_dark_A100))
        if (num != numOfQuestions - 1)  binding.btnNext.setBackgroundColor(getColor(R.color.green_A100))
        else    binding.btnNext.setBackgroundColor(getColor(R.color.green_dark_A100))
        if (userAnswerList[num] == UserAnswer.NOANSWER) {
            binding.btnFalse.setBackgroundColor(getColor(R.color.blue_A100))
            binding.btnTrue.setBackgroundColor(getColor(R.color.pink_A100))
            binding.btnCheat.setBackgroundColor(getColor(R.color.red_A100))
        }
        else {
            binding.btnFalse.setBackgroundColor(getColor(R.color.blue_dark_A100))
            binding.btnTrue.setBackgroundColor(getColor(R.color.pink_dark_A100))
            binding.btnCheat.setBackgroundColor(getColor(R.color.red_dark_A100))
        }
    }
}

enum class UserAnswer{
    NOANSWER,
    TRUE,
    FALSE
}