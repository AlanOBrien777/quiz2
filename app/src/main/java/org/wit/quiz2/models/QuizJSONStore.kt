package org.wit.quiz2.models

import android.content.Context
import android.net.Uri
import com.google.gson.*
import com.google.gson.reflect.TypeToken
import org.wit.quiz2.models.QuizStore
import org.wit.quiz2.helpers.*
import timber.log.Timber
import java.lang.reflect.Type
import java.util.*

const val JSON_FILE = "quizzes.json"
val gsonBuilder: Gson = GsonBuilder().setPrettyPrinting()
    .registerTypeAdapter(Uri::class.java, UriParser())
    .create()
val listType: Type = object : TypeToken<ArrayList<QuizModel>>() {}.type

fun generateRandomId(): Long {
    return Random().nextLong()
}

class QuizJSONStore(private val context: Context) : QuizStore {

    var quizzes = mutableListOf<QuizModel>()

    init {
        if (exists(context, JSON_FILE)) {
            deserialize()
        }
    }

    override fun findAll(): MutableList<QuizModel> {
        logAll()
        return quizzes
    }

    override fun create(quiz: QuizModel) {
        quiz.id = generateRandomId()
        quizzes.add(quiz)
        serialize()
    }


    override fun update(quiz: QuizModel) {
        val quizzesList = findAll() as ArrayList<QuizModel>
        var foundQuiz: QuizModel? = quizzesList.find { p -> p.id == quiz.id }
        if (foundQuiz != null) {
            foundQuiz.title = quiz.title
            foundQuiz.description = quiz.description
            foundQuiz.image = quiz.image
            foundQuiz.lat = quiz.lat
            foundQuiz.lng = quiz.lng
            foundQuiz.zoom = quiz.zoom
        }
        serialize()
    }

    private fun serialize() {
        val jsonString = gsonBuilder.toJson(quizzes, listType)
        write(context, JSON_FILE, jsonString)
    }

    private fun deserialize() {
        val jsonString = read(context, JSON_FILE)
        quizzes = gsonBuilder.fromJson(jsonString, listType)
    }

    private fun logAll() {
        quizzes.forEach { Timber.i("$it") }
    }
}

class UriParser : JsonDeserializer<Uri>,JsonSerializer<Uri> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): Uri {
        return Uri.parse(json?.asString)
    }

    override fun serialize(
        src: Uri?,
        typeOfSrc: Type?,
        context: JsonSerializationContext?
    ): JsonElement {
        return JsonPrimitive(src.toString())
    }
}