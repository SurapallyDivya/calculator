package com.example.calapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.lang.ArithmeticException

private lateinit var tv:TextView
    var lastNumeric: Boolean = false
    var lastDot : Boolean = false
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tv=findViewById(R.id.tv)
    }
    fun onClick(view: View){
        tv.append((view as Button).text)
        lastNumeric=true
    }
    fun onClear(view: View){
        tv.text=""
        lastNumeric=false
        lastDot=false
    }
    fun onDecimalPoint(view: View){
        if(lastNumeric && !lastDot){
            tv.append(".")
            lastNumeric=false
            lastDot=true
        }
    }
    fun onOperator(view: View){
        if(lastNumeric && !isOperatorAdded(tv.text.toString())){
            tv.append((view as Button).text)
            lastNumeric=false
            lastDot=false
        }
    }
    private fun removeZeroAfterDot(result:String):String{
        var value=result
        if(result.contains("0"))
            value=result.substring(0,result.length-2)
        return value
    }
    fun onEqual(view: View){
        if(lastNumeric){
            var result=tv.text.toString()
            var prefix=""
            try{
                if(result.startsWith("-")){
                    prefix="-"
                    result=result.substring(1)
                }
              if(result.contains("-")){
                  val splitvalue=result.split("-")

                  var one=splitvalue[0]
                  var two=splitvalue[1]
                  if(!prefix.isEmpty()){
                      one=prefix+one

                  }
                  tv.text=removeZeroAfterDot((one.toDouble()-two.toDouble()).toString())
              }
                else if(result.contains("+")){
                    val splitvalue=result.split("+")

                    var one=splitvalue[0]
                    var two=splitvalue[1]
                    if(!prefix.isEmpty()){
                        one=prefix+one

                    }
                    tv.text=removeZeroAfterDot((one.toDouble()+two.toDouble()).toString())
                }
                else if(result.contains("*")){
                    val splitvalue=result.split("*")

                    var one=splitvalue[0]
                    var two=splitvalue[1]
                    if(!prefix.isEmpty()){
                        one=prefix+one

                    }
                    tv.text=removeZeroAfterDot((one.toDouble()*two.toDouble()).toString())
                }
                else if(result.contains("/")){
                    val splitvalue=result.split("/")

                    var one=splitvalue[0]
                    var two=splitvalue[1]
                    if(!prefix.isEmpty()){
                        one=prefix+one

                    }
                    tv.text=removeZeroAfterDot((one.toDouble()/two.toDouble()).toString())
                }
            }
            catch(e:ArithmeticException){
                e.printStackTrace()
            }
        }
    }
    private fun isOperatorAdded(value: String):Boolean{
        return if(value.startsWith("-")){
            false
        }
        else{
            value.contains("/")|| value.contains("*")||value.contains("+")|| value.contains("-")
        }
    }
}