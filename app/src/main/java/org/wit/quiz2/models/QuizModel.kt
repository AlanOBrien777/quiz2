package org.wit.quiz2.models

import android.net.Uri


data class QuizModel(var id: Long = 0,
                          var title: String = "",
                          var description: String = "",
                          var image: Uri = Uri.EMPTY)
