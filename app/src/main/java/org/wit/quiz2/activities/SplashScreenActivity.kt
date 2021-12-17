package org.wit.quiz2.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import org.wit.quiz2.databinding.ActivityPlayBinding
import org.wit.quiz2.databinding.ActivitySplashScreenBinding
import org.wit.quiz2.models.QuizJSONStore
import org.wit.quiz2.models.QuizModel
import timber.log.Timber
import timber.log.Timber.i
import java.util.*

class SplashScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding
    private lateinit var refreshIntentLauncher : ActivityResultLauncher<Intent>
    private lateinit var bind: ActivityPlayBinding

    private var quizzes = mutableListOf<QuizModel>()
    var playQuiz = QuizModel()

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
            val intent = Intent(this, PlayActivity::class.java)
            startActivity(intent)

            randomQuiz()

            bind.quizTitle.text = playQuiz.title
            bind.genre.text = playQuiz.genre
            bind.q1.text = playQuiz.question1
            bind.q2.text = playQuiz.question2
            bind.q3.text = playQuiz.question3
            bind.q4.text = playQuiz.question4
            bind.q5.text = playQuiz.question5
        }
    }

    fun randomQuiz(){
        var newId = Random().nextInt(quizzes.size)
        playQuiz = quizzes[newId]
        println("random number = $newId")
    }
}