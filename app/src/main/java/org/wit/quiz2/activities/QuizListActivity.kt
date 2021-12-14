package org.wit.quiz2.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import org.wit.quiz2.R
import org.wit.quiz2.activities.QuizActivity
import org.wit.quiz2.adapters.QuizAdapter
import org.wit.quiz2.adapters.QuizListener
import org.wit.quiz2.databinding.ActivityQuizListBinding
import org.wit.quiz2.main.MainApp
import org.wit.quiz2.models.QuizModel

class QuizListActivity : AppCompatActivity(), QuizListener/*, MultiplePermissionsListener*/ {

    lateinit var app: MainApp
    private lateinit var binding: ActivityQuizListBinding
    private lateinit var refreshIntentLauncher : ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toolbar.title = title
        setSupportActionBar(binding.toolbar)

        app = application as MainApp

        val layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager

        loadQuizzes()
        registerRefreshCallback()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_add -> {
                val launcherIntent = Intent(this, QuizActivity::class.java)
                refreshIntentLauncher.launch(launcherIntent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onQuizClick(quiz: QuizModel) {
        val launcherIntent = Intent(this, QuizActivity::class.java)
        launcherIntent.putExtra("quiz_edit", quiz)
        refreshIntentLauncher.launch(launcherIntent)
    }

    private fun registerRefreshCallback() {
        refreshIntentLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult())
            { loadQuizzes() }
    }

    private fun loadQuizzes() {
        showQuizzes(app.quizzes.findAll())
    }

    fun showQuizzes (quizzes: List<QuizModel>) {
        binding.recyclerView.adapter = QuizAdapter(quizzes, this)
        binding.recyclerView.adapter?.notifyDataSetChanged()
    }

}

