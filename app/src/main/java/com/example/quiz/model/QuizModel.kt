package com.example.quiz.model

data class QuizModel(
    val id: Int,
    val question: String,
    val firstOption: String,
    val secondOption: String,
    val thirdOption: String,
    val forthOption: String,
    val answer: String
) {


}