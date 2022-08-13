package com.example.quiz.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.quiz.R
import com.example.quiz.model.QuizModel
import kotlinx.android.synthetic.main.item_layout.view.*

class QuizAdapter(
    private val context: Context,
    private val quizList: MutableList<QuizModel>
) : RecyclerView.Adapter<QuizAdapter.QuizViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuizViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return QuizViewHolder(view)
    }

    override fun onBindViewHolder(holder: QuizViewHolder, position: Int) {
        val quizModel = quizList[position]
        holder.setData(quizModel)
    }

    override fun getItemCount(): Int {
        return quizList.size
    }

    class QuizViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        fun setData(quizModel: QuizModel) {
            view.tvQuestion.text = quizModel.question
            view.tvOption1.text = quizModel.firstOption
            view.tvOption2.text = quizModel.secondOption
            view.tvOption3.text = quizModel.thirdOption
            view.tvOption4.text = quizModel.forthOption
        }
    }

}