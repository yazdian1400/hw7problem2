package ir.homework.hw7problem2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ir.homework.hw7problem2.databinding.ActivityCheatBinding

class CheatActivity : AppCompatActivity() {
    lateinit var binding: ActivityCheatBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cheat)
        binding = ActivityCheatBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}