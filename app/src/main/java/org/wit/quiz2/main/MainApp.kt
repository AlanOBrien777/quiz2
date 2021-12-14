package org.wit.quiz2.main

import android.app.Application
import org.wit.quiz2.models.QuizJSONStore
import org.wit.quiz2.models.QuizStore
import timber.log.Timber
import timber.log.Timber.i

class MainApp : Application() {

    lateinit var quizzes: QuizStore

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        quizzes = QuizJSONStore(applicationContext)
        i("Quiz started")
    }
}