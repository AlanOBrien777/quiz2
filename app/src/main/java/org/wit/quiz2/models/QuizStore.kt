package org.wit.quiz2.models

import org.wit.quiz2.models.QuizModel

interface QuizStore {
    fun findAll(): List<QuizModel>
    fun create(quiz: QuizModel)
    fun update(quiz: QuizModel)
}