package com.example.countryquizapp.fragments

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.countryquizapp.Question
import com.example.countryquizapp.R
import com.example.countryquizapp.databinding.FragmentQuestionBinding
import com.example.countryquizapp.model.QuestionData


class QuestionFragment : Fragment() {
    private var _binding:FragmentQuestionBinding?=null
    private val binding get() = _binding!!
    private var questionList:ArrayList<QuestionData>?=null
    private var curentPosition:Int=1
    private var selectedOptionPosition=0
    var runnable:Runnable=Runnable{}
    var handler:Handler=Handler(Looper.getMainLooper())
    private var time:Int=10
    private var correct:Int=0
    private var wrong:Int=0
    private var pass:Int=0
    private val nameArg:QuestionFragmentArgs by navArgs()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= FragmentQuestionBinding.inflate(inflater,container,false)
        // Inflate the layout for this fragment
        return binding.root
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val getArg=nameArg.username
        binding.nameText.text=getArg
        questionList=Question.getQuestion()
        getQuestions()
        binding.submitButton.isEnabled=false
        onClick()


    }



    @SuppressLint("SetTextI18n")
    private fun getQuestions(){
        time()
        binding.submitButton.isEnabled=false
        val question= questionList!![curentPosition-1]
        defaultOptions()
        binding.questionTextView.text=question.question
        binding.imageView.setImageResource(question.image)
        binding.optionOneTextView.text=question.optionone
        binding.optionTwoTextView.text=question.optiontwo
        binding.optionThreeTextView.text=question.optionthree
        binding.optionFourTextView.text=question.optionfour

        binding.progressBar.progress=curentPosition
        binding.questionNumber.text="$curentPosition"+"/"+"${binding.progressBar.max}"
    }

    @SuppressLint("SetTextI18n")
    private fun time(){
        time=10
        runnable= Runnable {
            time--
            binding.timeText.text="$time"
            handler.postDelayed(runnable,1000)
            if (binding.timeText.text=="0"){
                handler.removeCallbacks(runnable)
                time=10
                val question= questionList!![curentPosition-1]
                setColor(question.correctanswer,R.drawable.correctbackground)
                binding.submitButton.isEnabled=true
                pass++
                binding.passText.text="Pass:$pass"

            }

        }
        handler.post(runnable)
    }

    private fun defaultOptions(){
        val options=ArrayList<TextView>()
        options.add(0,binding.optionOneTextView)
        options.add(1,binding.optionTwoTextView)
        options.add(2,binding.optionThreeTextView)
        options.add(3,binding.optionFourTextView)

        for (option in options){
            option.setTextColor(Color.parseColor("#888888"))
            option.background=ContextCompat.getDrawable(requireContext(),R.drawable.defaultoptionback)
        }
    }

    private fun selectedOption(tv:TextView,selectedOp:Int){
        binding.submitButton.isEnabled=true
        defaultOptions()

        selectedOptionPosition=selectedOp

        tv.setTextColor(Color.parseColor("#9C27B0"))
        tv.background=ContextCompat.getDrawable(requireContext(),R.drawable.selectedoptionback)

    }

    private fun onClick(){
        binding.optionOneTextView.setOnClickListener {
            selectedOption(binding.optionOneTextView,1)
        }

        binding.optionTwoTextView.setOnClickListener{
            selectedOption(binding.optionTwoTextView,2)
        }

        binding.optionThreeTextView.setOnClickListener{
            selectedOption(binding.optionThreeTextView,3)
        }

        binding.optionFourTextView.setOnClickListener{
            selectedOption(binding.optionFourTextView,4)
        }

        binding.submitButton.setOnClickListener {
            handler.removeCallbacks(runnable)
            if (selectedOptionPosition!=0){
                val question= questionList!![curentPosition -1]
                if (selectedOptionPosition!=question.correctanswer){
                    setColor(selectedOptionPosition,R.drawable.wrongbackground)
                    wrong++
                    binding.wrongText.text="Wrong:$wrong"
                }else{
                    correct++
                    binding.correctText.text="Correct:$correct"

                }
                setColor(question.correctanswer,R.drawable.correctbackground)


                if (curentPosition==questionList!!.size){
                    binding.submitButton.text="Finish"
                    binding.submitButton.setOnClickListener {
                        val correct=binding.correctText.text.toString()
                        val wrong=binding.wrongText.text.toString()
                        val result=QuestionFragmentDirections.actionQuestionFragmentToResultFragment(correct, wrong)
                        findNavController().navigate(result)
                    }

                }else{
                    binding.submitButton.text="Next Question"
                }

            }else{
                curentPosition++
                when{
                    curentPosition<=questionList!!.size->{
                        getQuestions()
                        binding.submitButton.text="Submit"
                    }

                }
            }
            selectedOptionPosition=0
        }
    }

    private fun setColor(option:Int,color:Int){
        when(option){
            1->{
                binding.optionOneTextView.background=ContextCompat.getDrawable(requireContext(),color)
            }
            2->{
                binding.optionTwoTextView.background=ContextCompat.getDrawable(requireContext(),color)
            }
            3->{
                binding.optionThreeTextView.background=ContextCompat.getDrawable(requireContext(),color)
            }
            4->{
                binding.optionFourTextView.background=ContextCompat.getDrawable(requireContext(),color)
            }
        }

    }
}