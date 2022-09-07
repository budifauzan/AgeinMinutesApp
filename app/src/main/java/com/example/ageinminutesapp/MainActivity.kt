package com.example.ageinminutesapp

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private var btnSelectDate: Button? = null
    private var tvSelectedDate: TextView? = null
    private var tvResult: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnSelectDate = findViewById(R.id.btn_activity_main_select_date)
        btnSelectDate?.setOnClickListener {
            selectDate()
        }
    }

    private fun selectDate() {
        val myCalendar = Calendar.getInstance()
        val selectedYear = myCalendar.get(Calendar.YEAR)
        val selectedMonth = myCalendar.get(Calendar.MONTH)
        val selectedDay = myCalendar.get(Calendar.DAY_OF_MONTH)
        val datePickerDialog = DatePickerDialog(
            this, { _, year, month, day ->
                //Display selected date
                val selectedDate = "$day/${month + 1}/$year"
                tvSelectedDate = findViewById(R.id.tv_activity_main_date)
                tvSelectedDate?.text = selectedDate

                val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                val date = simpleDateFormat.parse(selectedDate)

                date?.let {
                    //Convert selected date in minutes
                    val selectedDateinMinute = date.time / 60000

                    //Get current date then convert in minutes
                    val currentDate =
                        simpleDateFormat.parse(simpleDateFormat.format(System.currentTimeMillis()))
                    currentDate?.let {
                        val currentDateinMinutes = currentDate.time / 60000

                        //Calculate result then display it
                        val ageinMinutes = currentDateinMinutes - selectedDateinMinute
                        tvResult = findViewById(R.id.tv_activity_main_result)
                        tvResult?.text = ageinMinutes.toString()
                    }

                }

            },
            selectedYear,
            selectedMonth,
            selectedDay
        )

        datePickerDialog.datePicker.maxDate = System.currentTimeMillis() - 86400000
        datePickerDialog.show()
    }
}