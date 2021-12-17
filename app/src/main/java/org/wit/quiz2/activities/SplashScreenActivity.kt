package org.wit.quiz2.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import org.wit.quiz2.databinding.ActivitySplashScreenBinding
import timber.log.Timber
import timber.log.Timber.i

class SplashScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding
    private lateinit var refreshIntentLauncher : ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnStart.setOnClickListener() {
            i("start Button Pressed")
            val intent = Intent(this, QuizListActivity::class.java)
            startActivity(intent)
        }

        binding.btnPlay.setOnClickListener() {
            i("play Button Pressed")
            val intent = Intent(this, QuizListActivity::class.java)
            startActivity(intent)
        }
    }
}