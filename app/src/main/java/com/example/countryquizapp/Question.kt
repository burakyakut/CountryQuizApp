package com.example.countryquizapp

import com.example.countryquizapp.model.QuestionData

object Question {

    fun getQuestion():ArrayList<QuestionData>{

        val questionList=ArrayList<QuestionData>()

        val question1=QuestionData(1,R.drawable.turkiye,"What country does this flag belong to?","Türkiye","Germany","Netherlands","USA",1
        )
        questionList.add(question1)
        val question2=QuestionData(2,R.drawable.america,"What country does this flag belong to?","Türkiye","Germany","Netherlands","USA",4
        )
        questionList.add(question2)
        val question3=QuestionData(3,R.drawable.norway,"What country does this flag belong to?","Norway","Germany","Netherlands","USA",1
        )
        questionList.add(question3)
        val question4=QuestionData(4,R.drawable.denmark,"What country does this flag belong to?","Türkiye","England","Denmark","USA",3
        )
        questionList.add(question4)
        val question5=QuestionData(5,R.drawable.england,"What country does this flag belong to?","England","Türkiye","Netherlands","USA",1
        )
        questionList.add(question5)
        return questionList
    }
}