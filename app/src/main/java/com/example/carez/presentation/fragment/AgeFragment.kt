package com.example.carez.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.NumberPicker
import androidx.fragment.app.Fragment
import com.example.carez.R
import java.util.Calendar

class AgeFragment : Fragment() {

    private lateinit var dayPicker: NumberPicker
    private lateinit var monthPicker: NumberPicker
    private lateinit var yearPicker: NumberPicker
    private var selectedDay: Int = 1
    private var selectedMonth: Int = 1
    private var selectedYear: Int = 2000

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_age, container, false)

        dayPicker = view.findViewById(R.id.dayPicker)
        monthPicker = view.findViewById(R.id.monthPicker)
        yearPicker = view.findViewById(R.id.yearPicker)

        dayPicker.minValue = 1
        dayPicker.maxValue = 31
        dayPicker.setOnValueChangedListener { _, _, newVal -> selectedDay = newVal }

        monthPicker.minValue = 1
        monthPicker.maxValue = 12
        monthPicker.displayedValues = arrayOf(
            "Tháng 1", "Tháng 2", "Tháng 3", "Tháng 4",
            "Tháng 5", "Tháng 6", "Tháng 7", "Tháng 8",
            "Tháng 9", "Tháng 10", "Tháng 11", "Tháng 12"
        )
        monthPicker.setOnValueChangedListener { _, _, newVal -> selectedMonth = newVal }

        yearPicker.minValue = 1900
        yearPicker.maxValue = 2100
        yearPicker.value = 2000
        selectedYear = 2000
        yearPicker.setOnValueChangedListener { _, _, newVal -> selectedYear = newVal }

        return view
    }

    fun getAge(): Int {
        val day = selectedDay
        val month = selectedMonth
        val year = selectedYear

        val today = Calendar.getInstance()
        val birthDate = Calendar.getInstance().apply { set(year, month - 1, day) }

        var age = today.get(Calendar.YEAR) - birthDate.get(Calendar.YEAR)
        if (today.get(Calendar.DAY_OF_YEAR) < birthDate.get(Calendar.DAY_OF_YEAR)) age--

        return age
    }
}
