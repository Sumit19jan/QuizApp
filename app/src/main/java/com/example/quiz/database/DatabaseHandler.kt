package com.example.quiz.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import com.example.quiz.model.QuizModel

class DatabaseHandler(private val context: Context) :
    SQLiteOpenHelper(context, DATA_BASE, null, VERSION) {

    companion object {
        private val DATA_BASE = "Quiz_db"
        private val VERSION = 1
        private val TABLE_NAME = "Quiz_Table"
        private val ID = "ID"
        private val QUESTION = "QUESTION"
        private val FIRST_OPTION = "FIRST_OPTION"
        private val SECOND_OPTION = "SECOND_OPTION"
        private val THIRD_OPTION = "THIRD_OPTION"
        private val FORTH_OPTION = "FORTH_OPTION"
        private val ANSWER = "ANSWER"

    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = "CREATE TABLE $TABLE_NAME($ID INTEGER PRIMARY KEY,$QUESTION TEXT," +
                "$FIRST_OPTION TEXT,$SECOND_OPTION TEXT,$THIRD_OPTION TEXT,$FORTH_OPTION TEXT," +
                "$ANSWER TEXT)"

        db?.execSQL(createTable)
    }

    fun insertData(
        question: String, first_Option: String, second_Option: String, third_Option: String,
        forth_Option: String, answer: String
    ) {

        val db = writableDatabase

        val values = ContentValues()
        values.put(QUESTION, question)
        values.put(FIRST_OPTION, first_Option)
        values.put(SECOND_OPTION, second_Option)
        values.put(THIRD_OPTION, third_Option)
        values.put(FORTH_OPTION, forth_Option)
        values.put(ANSWER, answer)

        val id = db.let {
            it.insert(TABLE_NAME, null, values)
        }

        if (id.toInt() == -1) {
            Toast.makeText(context, "error", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(context, "success", Toast.LENGTH_LONG).show()
        }
    }

    fun updateQuiz(
        id: Int,
        question: String,
        first_Option: String,
        second_Option: String,
        third_Option: String,
        forth_Option: String,
        answer: String
    ) {

        val db = writableDatabase

        val values = ContentValues()
        values.put(QUESTION, question)
        values.put(FIRST_OPTION, first_Option)
        values.put(SECOND_OPTION, second_Option)
        values.put(THIRD_OPTION, third_Option)
        values.put(FORTH_OPTION, forth_Option)
        values.put(ANSWER, answer)
        val rowAffected = db.update(TABLE_NAME, values, "ID= $id", null)

        if (rowAffected > 0) {
            Toast.makeText(context, "success", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(context, "error", Toast.LENGTH_LONG).show()
        }
    }

    fun deleteRow(id: Int) {
        val db = readableDatabase

        db.delete(TABLE_NAME, "ID = $id", null)
    }

    fun fetchData(): MutableList<QuizModel> {

        val db = readableDatabase
        val quizList = mutableListOf<QuizModel>()
        val query = "select * from $TABLE_NAME"

        val cursor = db.rawQuery(query, null)
        val questionIndex = cursor.getColumnIndex(QUESTION)
        val firstOptionIndex = cursor.getColumnIndex(FIRST_OPTION)
        val secondOptionIndex = cursor.getColumnIndex(SECOND_OPTION)
        val thirdOptionIndex = cursor.getColumnIndex(THIRD_OPTION)
        val forthOptionIndex = cursor.getColumnIndex(FORTH_OPTION)
        val answerIndex = cursor.getColumnIndex(ANSWER)
        val idIndex = cursor.getColumnIndex(ID)

        if (cursor != null && cursor.count > 0) {
            cursor.moveToFirst()

            do {
                val id = cursor.getInt(idIndex)
                val question = cursor.getString(questionIndex)
                val firstOption = cursor.getString(firstOptionIndex)
                val secondOption = cursor.getString(secondOptionIndex)
                val thirdOption = cursor.getString(thirdOptionIndex)
                val forthOption = cursor.getString(forthOptionIndex)
                val answer = cursor.getString(answerIndex)

                val quizModel =
                    QuizModel(
                        id,
                        question,
                        firstOption,
                        secondOption,
                        thirdOption,
                        forthOption,
                        answer
                    )
                quizList.add(quizModel)

            } while (cursor.moveToNext())

            cursor.close()
        }
        return quizList
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }
}