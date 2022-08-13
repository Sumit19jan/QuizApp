package com.example.quiz.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

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


    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }
}