package ir.homework.hw7problem2

import android.app.Activity
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import ir.homework.hw7problem2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    val questionList = mutableListOf<Question>()
    var num = 0

    companion object{
        const val NUM_OF_QUESTIONS = 10
        const val NUM = "num"
        const val CHEATING_ARRAY = "cheatingArray"
        const val USER_ANSWER_ARRAY = "UserAnswerArray"
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setData()
        handleConfigChange(savedInstanceState)
        initViews()
        onClickListeners()
    }

    private fun MainActivity.handleConfigChange(savedInstanceState: Bundle?) {
        if (savedInstanceState != null) {
            with(savedInstanceState) {
                num = getInt(NUM)
                val cheatingArray = getBooleanArray(CHEATING_ARRAY)
                questionList.forEachIndexed { i, question ->
                    question.hasCheated =
                        cheatingArray?.get(i) ?: false
                }
                val userAnswerArray = getParcelableArray(USER_ANSWER_ARRAY)
                questionList.forEachIndexed { i, question ->
                    question.userAnswer =
                        (userAnswerArray?.get(i) as UserAnswer)
                }
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.run{
            putInt(NUM, num)
            val cheatingArray = questionList.map{it.hasCheated}.toBooleanArray()
            putBooleanArray(CHEATING_ARRAY ,cheatingArray)
            var userAnswerArray = questionList.map{it.userAnswer}.toTypedArray()
            putParcelableArray(USER_ANSWER_ARRAY, userAnswerArray)
        }
        super.onSaveInstanceState(outState)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun onClickListeners() {
        binding.btnTrue.setOnClickListener {
            questionList[num].userAnswer = UserAnswer.TRUE
            binding.btnTrue.isEnabled = false
            binding.btnTrue.setBackgroundColor(getColor(R.color.pink_dark_A100))
            binding.btnFalse.isEnabled = false
            binding.btnFalse.setBackgroundColor(getColor(R.color.blue_dark_A100))
            binding.btnCheat.isEnabled = false
            binding.btnCheat.setBackgroundColor(getColor(R.color.red_dark_A100))
            messageAnswer(questionList[num].answer)
        }
        binding.btnFalse.setOnClickListener {
            questionList[num].userAnswer = UserAnswer.FALSE
            binding.btnTrue.isEnabled = false
            binding.btnTrue.setBackgroundColor(getColor(R.color.pink_dark_A100))
            binding.btnFalse.isEnabled = false
            binding.btnFalse.setBackgroundColor(getColor(R.color.blue_dark_A100))
            binding.btnCheat.isEnabled = false
            binding.btnCheat.setBackgroundColor(getColor(R.color.red_dark_A100))
            messageAnswer(!questionList[num].answer)
        }
        binding.btnNext.setOnClickListener {
            num++
            changePage()
        }
        binding.btnPrev.setOnClickListener {
            num--
            changePage()
        }
        binding.btnCheat.setOnClickListener {
            val intent = Intent(this, CheatActivity::class.java)
            intent.putExtra("answer", questionList[num].answer)
            startActivityForResult(intent, 101)
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 101 && resultCode == Activity.RESULT_OK && data != null){
            val hasCheated = data.getBooleanExtra("hasCheated",false)
            if (hasCheated == true) questionList[num].hasCheated = true
        }
    }
    private fun setData(){
        questionList.add(Question(getString(R.string.question1),(getString(R.string.answer1)).toBoolean()))
        questionList.add(Question(getString(R.string.question2),getString(R.string.answer2).toBoolean()))
        questionList.add(Question(getString(R.string.question3),getString(R.string.answer3).toBoolean()))
        questionList.add(Question(getString(R.string.question4),getString(R.string.answer4).toBoolean()))
        questionList.add(Question(getString(R.string.question5),getString(R.string.answer5).toBoolean()))
        questionList.add(Question(getString(R.string.question6),getString(R.string.answer6).toBoolean()))
        questionList.add(Question(getString(R.string.question7),getString(R.string.answer7).toBoolean()))
        questionList.add(Question(getString(R.string.question8),getString(R.string.answer8).toBoolean()))
        questionList.add(Question(getString(R.string.question9),getString(R.string.answer9).toBoolean()))
        questionList.add(Question(getString(R.string.question10),getString(R.string.answer10).toBoolean()))
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun initViews(){
        //setQuestions()
        changePage()
    }
    fun messageAnswer(boolean: Boolean){
        if (boolean){
            Toast.makeText(this, "Correct",Toast.LENGTH_LONG).show()
            if (questionList[num].hasCheated == true) Toast.makeText(this, "Cheating is Wrong.",Toast.LENGTH_LONG).show()
        }
        else    Toast.makeText(this, "Incorrect",Toast.LENGTH_LONG).show()
    }
    @RequiresApi(Build.VERSION_CODES.M)
    fun changePage() {
        binding.tvQuestion.text = questionList[num].question
        binding.btnPrev.isEnabled = num != 0
        binding.btnNext.isEnabled = num != (NUM_OF_QUESTIONS - 1)
        binding.btnFalse.isEnabled = questionList[num].userAnswer == UserAnswer.NO_ANSWER
        binding.btnTrue.isEnabled = questionList[num].userAnswer == UserAnswer.NO_ANSWER
        binding.btnCheat.isEnabled = questionList[num].userAnswer == UserAnswer.NO_ANSWER
        if (num != 0)   binding.btnPrev.setBackgroundColor(getColor(R.color.green_A100))
        else    binding.btnPrev.setBackgroundColor(getColor(R.color.green_dark_A100))
        if (num != NUM_OF_QUESTIONS - 1)  binding.btnNext.setBackgroundColor(getColor(R.color.green_A100))
        else    binding.btnNext.setBackgroundColor(getColor(R.color.green_dark_A100))
        if (questionList[num].userAnswer == UserAnswer.NO_ANSWER) {
            binding.btnFalse.setBackgroundColor(getColor(R.color.blue_A100))
            binding.btnTrue.setBackgroundColor(getColor(R.color.pink_A100))
            binding.btnCheat.setBackgroundColor(getColor(R.color.red_A100))
        }
        else {
            binding.btnFalse.setBackgroundColor(getColor(R.color.blue_dark_A100))
            binding.btnTrue.setBackgroundColor(getColor(R.color.pink_dark_A100))
            binding.btnCheat.setBackgroundColor(getColor(R.color.red_dark_A100))
        }
        if ((questionList[num].userAnswer == UserAnswer.TRUE && questionList[num].answer
                    || questionList[num].userAnswer == UserAnswer.FALSE && !questionList[num].answer) && questionList[num].hasCheated)
            Toast.makeText(this, "Cheating is wrong.",Toast.LENGTH_LONG).show()
    }
}