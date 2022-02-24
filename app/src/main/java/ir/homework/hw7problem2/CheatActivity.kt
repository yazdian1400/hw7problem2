package ir.homework.hw7problem2

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import ir.homework.hw7problem2.databinding.ActivityCheatBinding


class CheatActivity : AppCompatActivity() {
    lateinit var binding: ActivityCheatBinding
    var hasCheated = false
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cheat)
        binding = ActivityCheatBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setTitle("GeoQuiz")
        val answer = intent.getBooleanExtra("answer", false)
        binding.btnShowAnswer.setOnClickListener{
            binding.tvCheatAnswer.text = answer.toString()
            binding.btnShowAnswer.setBackgroundColor(getColor(R.color.green_dark_A100))
            hasCheated = true
            Toast.makeText(this, "hasCheated" + hasCheated.toString(),Toast.LENGTH_LONG).show()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                val returnIntent = Intent()
                returnIntent.putExtra("hasCheated", hasCheated)
                setResult(Activity.RESULT_OK, returnIntent)
                //intent.putExtra("hasCheated", hasCheated)
                //startActivity(intent)
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}