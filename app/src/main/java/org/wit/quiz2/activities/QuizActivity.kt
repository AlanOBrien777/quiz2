package org.wit.quiz2.activities

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.Picasso
import org.wit.quiz2.R
import org.wit.quiz2.databinding.ActivityQuizBinding
import org.wit.quiz2.helpers.showImagePicker
import org.wit.quiz2.main.MainApp
import org.wit.quiz2.models.QuizModel
import timber.log.Timber
import timber.log.Timber.i

class QuizActivity : AppCompatActivity() {

    private lateinit var binding: ActivityQuizBinding
    var quiz = QuizModel()
    lateinit var app: MainApp
    private lateinit var imageIntentLauncher : ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var edit = false

        binding = ActivityQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toolbarAdd.title = title
        setSupportActionBar(binding.toolbarAdd)

        app = application as MainApp

        i("Quiz Activity started...")

        if (intent.hasExtra("quiz_edit")) {
            edit = true
            quiz = intent.extras?.getParcelable("quiz_edit")!!
            binding.quizTitle.setText(quiz.title)
            binding.description.setText(quiz.description)
            binding.btnAdd.setText(R.string.save_quiz)
            Picasso.get()
                .load(quiz.image)
                .into(binding.quizImage)
            if (quiz.image != Uri.EMPTY) {
                binding.chooseImage.setText(R.string.change_quiz_image)
            }
        }

        binding.btnAdd.setOnClickListener() {
            quiz.title = binding.quizTitle.text.toString()
            quiz.description = binding.description.text.toString()
            if (quiz.title.isEmpty()) {
                Snackbar.make(it,R.string.enter_quiz_title, Snackbar.LENGTH_LONG)
                    .show()
            } else {
                if (edit) {
                    app.quizzes.update(quiz.copy())
                } else {
                    app.quizzes.create(quiz.copy())
                }
            }
            i("add Button Pressed: $quiz")
            setResult(RESULT_OK)
            finish()
        }

        binding.chooseImage.setOnClickListener {
            showImagePicker(imageIntentLauncher)
        }

        registerImagePickerCallback()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_quiz, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_cancel -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun registerImagePickerCallback() {
        imageIntentLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult())
            { result ->
                when(result.resultCode){
                    RESULT_OK -> {
                        if (result.data != null) {
                            i("Got Result ${result.data!!.data}")
                            quiz.image = result.data!!.data!!
                            Picasso.get()
                                .load(quiz.image)
                                .into(binding.quizImage)
                            binding.chooseImage.setText(R.string.change_quiz_image)
                        } // end of if
                    }
                    RESULT_CANCELED -> { } else -> { }
                }
            }
    }
}