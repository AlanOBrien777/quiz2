package org.wit.quiz2.adapters

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.squareup.picasso.Picasso
import org.wit.quiz2.databinding.CardQuizBinding
import org.wit.quiz2.models.QuizModel

interface QuizListener {
    fun onQuizClick(quiz: QuizModel)
}

class QuizAdapter constructor(
    private var quizzes: List<QuizModel>,
    private val listener: QuizListener
) :
    RecyclerView.Adapter<QuizAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        val binding = CardQuizBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return MainHolder(binding)
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val quiz = quizzes[holder.adapterPosition]
        holder.bind(quiz, listener)
    }

    override fun getItemCount(): Int = quizzes.size

    class MainHolder(private val binding: CardQuizBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(quiz: QuizModel, listener: QuizListener) {
            binding.quizTitle.text = quiz.title
            binding.description.text = quiz.description
            Picasso.get().load(quiz.image).resize(200,200).into(binding.imageIcon)
//            Glide.with(binding.root)
//                .load(quiz.image)
//                .override(200,200)
//                .into(binding.imageIcon)
            binding.root.setOnClickListener { listener.onQuizClick(quiz) }
        }
    }
}
