package com.example.quiz.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.example.quiz.R
import com.example.quiz.adapter.QuizAdapter
import com.example.quiz.database.DatabaseHandler
import com.example.quiz.model.QuizModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var quizAdapter: QuizAdapter
    private var quizList: MutableList<QuizModel>? = null
    private val databaseHandler = DatabaseHandler(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnAdd.setOnClickListener {
            databaseHandler.insertData(
                "What is the capital of india", "Delhi",
                "Mumbai", "Kolkata", "Dhanbad", "Delhi"
            )
        }
        btnUpdate.setOnClickListener {
            databaseHandler.updateQuiz(
                4, "What is the capital of Uttar Pradesh", "Varanasi",
                "Ballia", "Lucknow", "Prayagraj", "Lucknow"
            )

        }
        btnDelete.setOnClickListener {
            databaseHandler.deleteRow(8)
        }
    }
}