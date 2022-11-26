package com.laksh.dobcal

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.service.autofill.FillEventHistory
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.time.Year
import java.util.*

class MainActivity : AppCompatActivity() {
    private var tvSelectedDate: TextView?= null
    private var tvAgeInMinuts: TextView?=null
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button: Button=findViewById(R.id.btnDatePicker)
        tvSelectedDate=findViewById(R.id.tvSelectedButton)
        tvAgeInMinuts=findViewById(R.id.tvAgeInMinuts)
        button.setOnClickListener {
          clickDatePicker()
        }
    }
    fun clickDatePicker(){
        val myCalander=Calendar.getInstance()
        val year = myCalander.get(Calendar.YEAR)
        val month = myCalander.get(Calendar.MONTH)
        val day = myCalander.get(Calendar.DAY_OF_MONTH)
       val dpd= DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener { view, selecctedyear, selectedmonth, selecteddayOfMonth ->
                Toast.makeText(
                    this,
                    "Year is $selecctedyear,Month is ${selectedmonth + 1},Day of the month is $selecteddayOfMonth",
                    Toast.LENGTH_LONG
                ).show()
                val selecteddates = "$selecteddayOfMonth,${selectedmonth + 1},$selecctedyear"
                tvSelectedDate?.text = selecteddates
                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                val theDate = sdf.parse(selecteddates)
                theDate?.let {
                    val selecteddateInMinutes = theDate.time / 60000
                    val currentDate=sdf.parse(sdf.format(System.currentTimeMillis()))
                    currentDate?.let{
                        val currentDateInMinutes = currentDate.time / 60000
                        val differenceInMunits=currentDateInMinutes-selecteddateInMinutes
                        tvAgeInMinuts?.text=differenceInMunits.toString()
                    }
                }


            },
                year,
            month,
            day
        )
        dpd.datePicker.maxDate=System.currentTimeMillis()-86400000
       dpd.show()



    }
}