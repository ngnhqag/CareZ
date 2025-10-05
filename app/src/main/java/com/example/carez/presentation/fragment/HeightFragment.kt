package com.example.carez.presentation.fragment

import android.os.Bundle
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.carez.R

class HeightFragment : Fragment() {
    private var heightValue: Int? = null

    fun getHeight(): Int? = heightValue

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = inflater.inflate(R.layout.fragment_height, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val edtHeight = view.findViewById<EditText>(R.id.edtHeight)
        heightValue = edtHeight.text?.toString()?.toIntOrNull()
        edtHeight.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                heightValue = s?.toString()?.toIntOrNull()
            }

            override fun afterTextChanged(s: android.text.Editable?) {}
        })
    }
}
