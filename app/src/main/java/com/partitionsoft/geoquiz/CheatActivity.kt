package com.partitionsoft.geoquiz

import android.annotation.SuppressLint
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.partitionsoft.geoquiz.databinding.ActivityCheatBinding

class CheatActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCheatBinding
    private var remainingTokens = 3


    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCheatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apiLevelTextView.text = "API Level ${Build.VERSION.SDK_INT}"

        updateTokenDisplay()

        binding.showAnswerButton.setOnClickListener {
            if (remainingTokens > 0) {
                // User has cheat tokens remaining
                // Decrement the token count
                remainingTokens--
                updateTokenDisplay()

                // Show the answer
                val answerText = intent.getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false).toString()
                binding.answerTextView.text = answerText
            } else {
                // User has no cheat tokens remaining, disable the button
                binding.showAnswerButton.isEnabled = false
            }
        }
    }

    private fun updateTokenDisplay() {
        // Update the display of remaining cheat tokens
        binding.showAnswerButton.text = "Show Answer ($remainingTokens left)"
    }

    companion object {
        private const val EXTRA_ANSWER_IS_TRUE = "com.example.geoquiz.answer_is_true"
    }
}