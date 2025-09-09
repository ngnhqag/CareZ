package com.example.carez.base

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.example.carez.databinding.ActivityMainBinding


abstract class BaseActivity<T : ViewBinding, V : BaseViewModel> : AppCompatActivity() {
    protected lateinit var binding: T               // lateinit var binding: ActivityMainBinding
    protected lateinit var viewModel: V

    protected abstract fun getViewModel(): V
    protected abstract fun createActivity() : T


    override fun onStart() {
        super.onStart()
    }

}