package com.example.carez.presentation.fragment.userinfo

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.carez.R

class NameFragment : Fragment() {
    private var nameValue: String? = null

    fun getName(): String? = nameValue?.takeIf { it.isNotBlank() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = inflater.inflate(R.layout.fragment_name, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val edtName = view.findViewById<EditText>(R.id.edtName)
        nameValue = edtName.text?.toString()
        edtName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                nameValue = s?.toString()
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }
}