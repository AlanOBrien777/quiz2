package org.wit.quiz2.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.wit.quiz2.R
import org.wit.quiz2.databinding.ActivityPlayBinding

class PlayActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPlayBinding

    var theQuiz = SplashScreenActivity().playQuiz

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlayBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.quizTitle.text = theQuiz.title
        binding.genre.text = theQuiz.genre
        binding.q1.text = theQuiz.question1
        binding.q2.text = theQuiz.question2
        binding.q3.text = theQuiz.question3
        binding.q4.text = theQuiz.question4
        binding.q5.text = theQuiz.question5

    }
}