package com.example.carez.di

import com.example.carez.data.remote.datasource.GoogleAuthDataSource
import com.example.carez.data.remote.datasource.GoogleAuthDataSourceImpl
import com.example.carez.data.repository.UserRepositoryImpl
import com.example.carez.domain.repository.UserRepository
import com.example.carez.domain.usecase.SignInWithGoogleUseCase
import com.example.carez.domain.usecase.SignOutUseCase
import com.example.carez.presentation.activity.main.MainViewModel
import com.example.carez.presentation.activity.signIn.SignInViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    // DataSource
    single<GoogleAuthDataSource> { GoogleAuthDataSourceImpl() }

    // Repository
    single<UserRepository> { UserRepositoryImpl(get()) }

    // UseCase
    factory { SignInWithGoogleUseCase(get()) }
    factory { SignOutUseCase(get()) }

    // ViewModel
    viewModel { SignInViewModel(get()) }
    viewModel { MainViewModel(get()) }
}