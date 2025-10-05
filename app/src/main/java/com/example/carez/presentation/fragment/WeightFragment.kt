package com.example.carez.presentation.fragment

import android.os.Bundle
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.carez.R

class WeightFragment : Fragment() {
    private var weightValue: Float? = null

    fun getWeight(): Float? = weightValue

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = inflater.inflate(R.layout.fragment_weight, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val edtWeight = view.findViewById<EditText>(R.id.edtWeight)
        weightValue = edtWeight.text?.toString()?.toFloatOrNull()
        edtWeight.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                weightValue = s?.toString()?.toFloatOrNull()
            }

            override fun afterTextChanged(s: android.text.Editable?) {}
        })
    }
}
