package com.example.carez.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.carez.databinding.SignInBinding
import com.example.carez.viewmodel.SignInViewModel
import androidx.activity.viewModels
import android.content.Intent
import com.example.carez.view.SignUpActivity

class SignInActivity : AppCompatActivity() {
    // dùng viewbinding đỡ phải findviewbyid
    private lateinit var binding: SignInBinding
    //khởi tạo viewmodel sống tồn tại song song với activity
    private val signInViewModel: SignInViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=SignInBinding.inflate(layoutInflater)
        setContentView(binding.root)
            binding.btnSignIn.setOnClickListener{
                val email = binding.edtuserName.text.toString()
                val password = binding.edtpassword.text.toString()
                if (email.isNotEmpty() && password.isNotEmpty()) {
                    signInViewModel.signIn(email, password)
                }
                else {
                    Toast.makeText(this,"Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show()
//                    phải dùng Toast.makeText vì print không hiên thị cho người dùng được Toast.LENGTH_SHORT cài thời gian hiển thị
                }
            }
        signInViewModel.signInState.observe(this) { result ->
            result.onSuccess { message ->
                        Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
                }
            result.onFailure { error ->
                Toast.makeText(this, error.message, Toast.LENGTH_SHORT).show()
            }
        }
        binding.txtSignUp.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }

}