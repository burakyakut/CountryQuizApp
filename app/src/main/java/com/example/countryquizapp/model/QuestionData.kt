package com.example.countryquizapp.model

data class QuestionData(
    val id:Int,
    val image:Int,
    val question:String,
    val optionone:String,
    val optiontwo:String,
    val optionthree:String,
    val optionfour:String,
    val correctanswer:Int
)
