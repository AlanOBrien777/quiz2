package org.wit.quiz2.models

import android.net.Uri
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class QuizModel(var id: Long = 0,
                          var title: String = "",
                          var genre: String = "",
                          var question1: String = "",
                          var question2: String = "",
                          var question3: String = "",
                          var question4: String = "",
                          var question5: String = "",
                          var answer1: String = "",
                          var answer2: String = "",
                          var answer3: String = "",
                          var answer4: String = "",
                          var answer5: String = "",
                          var image: Uri = Uri.EMPTY) :Parcelable
