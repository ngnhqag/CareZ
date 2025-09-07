package com.example.carez.view

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.carez.databinding.SignUpBinding
import com.example.carez.viewmodel.SignUpViewModel

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: SignUpBinding
    private val signUpViewModel: SignUpViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnsignUp.setOnClickListener {
            val id = binding.edtid.text.toString()
            val gmail = binding.edtGmail.text.toString()
            val passWord = binding.edtPassWord.text.toString()
            val rePassWord = binding.edtRePassWord.text.toString()
            if ( id.isEmpty() || gmail.isEmpty() || passWord.isEmpty() || rePassWord.isEmpty() ) {
                Toast.makeText(this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if ( passWord != rePassWord ) {
                Toast.makeText(this, "Mật khẩu không khớp", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            signUpViewModel.signUp(id,gmail,passWord) { success, message ->
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
                if (success) {
                    finish() // hoặc chuyển sang màn hình SignIn
                }
            }
        }
    }

}
