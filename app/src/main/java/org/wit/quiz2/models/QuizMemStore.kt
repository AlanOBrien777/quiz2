package org.wit.quiz2.models

import org.wit.quiz2.models.QuizStore
import timber.log.Timber.i

var lastId = 0L

internal fun getId(): Long {
    return lastId++
}

class QuizMemStore : QuizStore {

    val quizzes = ArrayList<QuizModel>()

    override fun findAll(): List<QuizModel> {
        return quizzes
    }

    override fun create(quiz: QuizModel) {
        quiz.id = getId()
        quizzes.add(quiz)
        logAll()
    }

    override fun update(quiz: QuizModel) {
        val foundQuiz: QuizModel? = quizzes.find { p -> p.id == quiz.id }
        if (foundQuiz != null) {
            foundQuiz.title = quiz.title
            foundQuiz.description = quiz.description
            foundQuiz.image = quiz.image
            foundQuiz.lat = quiz.lat
            foundQuiz.lng = quiz.lng
            foundQuiz.zoom = quiz.zoom
            logAll()
        }
    }

    private fun logAll() {
        quizzes.forEach { i("$it") }
    }
}