package com.example.rpncalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import com.example.rpncalculator.databinding.ActivityMainBinding
import kotlin.math.sqrt

class MainActivity : AppCompatActivity() {

    // This is to help reduce stress of
    private lateinit var binding: ActivityMainBinding

    private lateinit var firstTextView: TextView
    private lateinit var secondTextView: TextView
    private lateinit var thirdTextView: TextView
    private lateinit var fourthTextView: TextView

    // A list to hold my text views
    // private val list = mutableListOf<TextView>(firstTextView, secondTextView, thirdTextView, fourthTextView)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        // This variable will hold my operands temporarily and reused in futher assignment
        var text: String

        /*Finding the view for each textView on the calculator*/
        firstTextView = binding.fourthViewList
        secondTextView = binding.thirdViewList
        thirdTextView = binding.secondViewList
        fourthTextView = binding.firstViewList

        /*Setting on click listener on all button on the calculator and specifying which activity each will be performing*/
        binding.bNine.setOnClickListener {
            text = firstTextView.text.toString() + "9"
            firstTextView.text = text
        }

        binding.bEight.setOnClickListener {
            text = firstTextView.text.toString() + "8"
            firstTextView.text = text
        }

        binding.bSeven.setOnClickListener {
            text = firstTextView.text.toString() + "7"
            firstTextView.text = text
        }

        binding.bSix.setOnClickListener {
            text = firstTextView.text.toString() + "6"
            firstTextView.text = text
        }

        binding.bFive.setOnClickListener {
            text = firstTextView.text.toString() + "5"
            firstTextView.text = text
        }

        binding.bFour.setOnClickListener {
            text = firstTextView.text.toString() + "4"
            firstTextView.text = text
        }

        binding.bThree.setOnClickListener {
            text = firstTextView.text.toString() + "3"
            firstTextView.text = text
        }

        binding.bTwo.setOnClickListener {
            text = firstTextView.text.toString() + "2"
            firstTextView.text = text
        }

        binding.bOne.setOnClickListener {
            text = firstTextView.text.toString() + "1"
            firstTextView.text = text
        }

        binding.bZero.setOnClickListener {
            text = firstTextView.text.toString() + "0"
            firstTextView.text = text
        }

        binding.bAc.setOnClickListener {
            firstTextView.text = ""
            secondTextView.text = ""
            thirdTextView.text = ""
            fourthTextView.text = ""
        }

        binding.bC.setOnClickListener {
            firstTextView.text = ""
        }

        binding.bBackspace.setOnClickListener {
            val backSpace : String
            if (firstTextView.text.isNotEmpty()) {
                val stringBuilder : StringBuilder = java.lang.StringBuilder(firstTextView.text)

                stringBuilder.deleteCharAt(firstTextView.text.length - 1)
                backSpace = stringBuilder.toString()
                firstTextView.text = backSpace
            }
        }

        binding.bEnter.setOnClickListener { handlingTextViews() }

        binding.bSqrt.setOnClickListener { result("sqrt") }

        binding.bFactorial.setOnClickListener { result("factorial") }

        binding.bAddSub.setOnClickListener { result("+/-") }

        binding.bSimpleInterest.setOnClickListener { result("interest") }

        binding.bMax.setOnClickListener { result("max") }

        binding.bMin.setOnClickListener { result("min") }

        /*The operators in charge of operation of the calculator*/
        binding.bAddition.setOnClickListener { result("+") }

        binding.bSubtraction.setOnClickListener { result("-") }

        binding.bMultiply.setOnClickListener { result("×") }

        binding.bDivision.setOnClickListener { result("÷") }
    }

    /**
     * This function takes no arguments and is just the functionality code for how text moves from one textViews to another
     * as you append to each textView to perform operations*/
    private fun handlingTextViews() {
        if (firstTextView.text.isNotEmpty()) {
            if (secondTextView.text.isEmpty()) {
                secondTextView.text = firstTextView.text
                firstTextView.text = ""
            } else {
                if (thirdTextView.text.isEmpty()) {
                    thirdTextView.text = secondTextView.text
                    secondTextView.text = firstTextView.text
                    firstTextView.text = ""
                } else {
                    fourthTextView.text = thirdTextView.text
                    thirdTextView.text = secondTextView.text
                    secondTextView.text = firstTextView.text
                    firstTextView.text = ""
                }
            }
        } else {
            Log.d("Error", "Ensure there is a value in the firstTextView")
        }
    }

    /**
     * This below function could be regarded as the engine of this calculator.
     * It takes a string argument and compare the argument to list of options using when statement.
     * The arguments to be passed should be atleast one of the symbols as seen below.
     * Operations are performed by assigning what we have in first two textView from the bottom
     * to a variable. And perform arithmetic operations on those two values and store them in another variable
     * Then we call another function which takes a double argument called textViewHandling().*/
    private fun result(operator: String) {
        if (firstTextView.text.isEmpty() or  secondTextView.text.isEmpty()) {
            when (operator) {
                "sqrt" -> {
                    val squareRoot = sqrt(firstTextView.text.toString().toDouble())

                    firstTextView.text = squareRoot.toString()
                }
                "factorial" -> {
                    var fac = 1

                    val toInts = firstTextView.text.toString().toDouble()
                    for (i in 1..toInts.toInt()) {
                        fac *= i
                    }
                    val factorial = fac.toDouble()
                    firstTextView.text = factorial.toString()
                }
                "+/-" -> {
                    if (firstTextView.text.toString().toDouble() > 0) {
                        val addSub = (-1) * firstTextView.text.toString().toDouble()

                        firstTextView.text = addSub.toString()
                    } else {
                        val addSub = (-1) * firstTextView.text.toString().toDouble()

                        firstTextView.text = addSub.toString()
                    }
                }
            }
            Log.d("Error", "Make sure there are values in first two views")
        } else if (firstTextView.text.isEmpty() and  secondTextView.text.isEmpty()) {
            Log.d("Error", "Make sure there are values in first two views")
        } else {
            when (operator) {
                "+" -> {
                    val firstOperand = secondTextView.text.toString().toDouble()
                    val secondOperand = firstTextView.text.toString().toDouble()

                    val sum = firstOperand + secondOperand
                    textViewHandling(sum)
                }
                "-" -> {
                    val firstOperand = secondTextView.text.toString().toDouble()
                    val secondOperand = firstTextView.text.toString().toDouble()

                    val sub = firstOperand - secondOperand
                    textViewHandling(sub)
                }
                "×" -> {
                    val firstOperand = secondTextView.text.toString().toDouble()
                    val secondOperand = firstTextView.text.toString().toDouble()

                    val multiplication = firstOperand * secondOperand
                    textViewHandling(multiplication)
                }
                "÷" -> {
                    val firstOperand = secondTextView.text.toString().toDouble()
                    val secondOperand = firstTextView.text.toString().toDouble()

                    val division = firstOperand / secondOperand
                    textViewHandling(division)
                }
                /*"%" -> {
                    val firstOperand = secondTextView.text.toString().toDouble()
                    val secondOperand = firstTextView.text.toString().toDouble()

                    val modulus = firstOperand % secondOperand
                    textViewHandling(modulus)
                }*/
                "sqrt" -> {
                    val squareRoot = sqrt(firstTextView.text.toString().toDouble())

                    firstTextView.text = squareRoot.toString()
                }
                "factorial" -> {
                    var fac = 1

                    for (i in 1..firstTextView.text.toString().toInt()) {
                        fac *= i
                    }
                    val factorial = fac.toDouble()
                    firstTextView.text = factorial.toString()
                }
                "+/-" -> {
                    if (firstTextView.text.toString().toDouble() > 0) {
                        val addSub = (-1) * firstTextView.text.toString().toDouble()

                        firstTextView.text = addSub.toString()
                    } else {
                        val addSub = (-1) * firstTextView.text.toString().toDouble()

                        firstTextView.text = addSub.toString()
                    }
                }
                "interest" -> {
                    if (thirdTextView.text.isNotEmpty() && secondTextView.text.isNotEmpty() && thirdTextView.text.isNotEmpty()) {
                        val principal = thirdTextView.text.toString().toDouble()
                        val rate = secondTextView.text.toString().toDouble()
                        val period = firstTextView.text.toString().toDouble()

                        val interest = (principal * rate * period) / 100

                        if (fourthTextView.text.isEmpty()) {
                            thirdTextView.text = ""
                            secondTextView.text = ""
                            firstTextView.text = interest.toString()
                        } else {
                            fourthTextView.text = ""
                            thirdTextView.text = ""
                            secondTextView.text = fourthTextView.text
                            firstTextView.text = interest.toString()
                        }
                    } else {
                        Toast.makeText(this, "Requires three values", Toast.LENGTH_SHORT).show()
                    }
                }
                "max" -> {
                    val list = mutableListOf<Double>()
                    if (fourthTextView.text.isNotEmpty()) {list.add(fourthTextView.text.toString().toDouble())}
                    if (thirdTextView.text.isNotEmpty()) {list.add(thirdTextView.text.toString().toDouble())}
                    if (secondTextView.text.isNotEmpty()) {list.add(secondTextView.text.toString().toDouble())}
                    if (firstTextView.text.isNotEmpty()) {list.add(firstTextView.text.toString().toDouble())}

                    if (list.isNotEmpty()) {
                        val myMax = list.max()
                        handlingTextViews()
                        firstTextView.text = myMax.toString()
                    } else {
                        Toast.makeText(this, "Requires atleast two values", Toast.LENGTH_SHORT).show()
                    }
                    // if (firstTextView.text == fourthTextView.text || firstTextView.text == thirdTextView.text || firstTextView.text == secondTextView.text) {}
                }
                "min" -> {
                    val list = mutableListOf<Double>()
                    if (fourthTextView.text.isNotEmpty()) {
                        list.add(fourthTextView.text.toString().toDouble())
                    }
                    if (thirdTextView.text.isNotEmpty()) {
                        list.add(thirdTextView.text.toString().toDouble())
                    }
                    if (secondTextView.text.isNotEmpty()) {
                        list.add(secondTextView.text.toString().toDouble())
                    }
                    if (firstTextView.text.isNotEmpty()) {
                        list.add(firstTextView.text.toString().toDouble())
                    }

                    if (list.isNotEmpty()) {
                        val myMin = list.min()
                        handlingTextViews()
                        firstTextView.text = myMin.toString()
                    } else {
                        Toast.makeText(this, "Requires atleast two values", Toast.LENGTH_SHORT).show()
                    }
                    // if (firstTextView.text == fourthTextView.text || firstTextView.text == thirdTextView.text || firstTextView.text == secondTextView.text) {}
                }
            }
        }
    }

    /**
    * This is a function that takes a double argument and it is basically used to format the
     * textView after operations must have been performed. This is required to make sure there is free flow
     * and to make sure text are moving from one textView to another on operations.*/

    private fun textViewHandling(operation: Double) {
        if (thirdTextView.text.isEmpty()) {
            secondTextView.text = ""
        } else {
            secondTextView.text = thirdTextView.text

            if (fourthTextView.text.isEmpty()) {
                thirdTextView.text = ""
                fourthTextView.text = ""
            } else {
                thirdTextView.text = fourthTextView.text
                fourthTextView.text = ""
            }
        }

        firstTextView.text = operation.toString()
    }
}
