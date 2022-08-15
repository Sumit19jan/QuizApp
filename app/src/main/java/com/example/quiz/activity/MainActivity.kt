package com.example.quiz.activity

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.quiz.R
import com.example.quiz.adapter.QuizAdapter
import com.example.quiz.database.DatabaseHandler
import com.example.quiz.listener.OnClickListener
import com.example.quiz.model.QuizModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_layout.*

class MainActivity : AppCompatActivity(), OnClickListener {

    lateinit var quizAdapter: QuizAdapter
    private var quizList: MutableList<QuizModel> = mutableListOf<QuizModel>()
    private val databaseHandler = DatabaseHandler(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        quizList = databaseHandler.fetchData()

        quizAdapter = QuizAdapter(this, quizList, this)

        btnAdd.setOnClickListener {
            databaseHandler.insertData(
                "What is the capital of india", "Delhi",
                "Mumbai", "Kolkata", "Dhanbad", "Delhi"
            )
            quizList.clear()
            quizList.addAll(databaseHandler.fetchData())
            quizAdapter.notifyDataSetChanged()


        }
        btnUpdate.setOnClickListener {
            databaseHandler.updateQuiz(
                3, "What is the capital of Uttar Pradesh", "Varanasi",
                "Ballia", "Lucknow", "Prayagraj", "Lucknow"
            )
            quizList.clear()
            quizList.addAll(databaseHandler.fetchData())
            quizAdapter.notifyDataSetChanged()

        }

        btnDelete.setOnClickListener {
            databaseHandler.deleteRow(1)
            quizList.clear()
            quizList.addAll(databaseHandler.fetchData())
            quizAdapter.notifyDataSetChanged()
        }



        recyclerView?.let {
            it.layoutManager = LinearLayoutManager(this)
            it.adapter = quizAdapter

        }
    }


    override fun onClick(quizModel: QuizModel) {

    }
}