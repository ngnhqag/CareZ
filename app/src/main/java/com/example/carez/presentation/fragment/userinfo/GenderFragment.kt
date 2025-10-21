package com.example.carez.presentation.fragment.userinfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import com.example.carez.R

class GenderFragment : Fragment() {

    private lateinit var optionMale: LinearLayout
    private lateinit var optionFemale: LinearLayout
    private var selectedGender: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_gender, container, false)

        optionMale = view.findViewById(R.id.optionMale)
        optionFemale = view.findViewById(R.id.optionFemale)

        optionMale.setOnClickListener { selectGender(true) }
        optionFemale.setOnClickListener { selectGender(false) }

        return view
    }

    private fun selectGender(isMale: Boolean) {
        optionMale.isSelected = isMale
        optionFemale.isSelected = !isMale
        selectedGender = if (isMale) "Male" else "Female"
    }

    fun getGender(): String? {
        return selectedGender
    }
}
