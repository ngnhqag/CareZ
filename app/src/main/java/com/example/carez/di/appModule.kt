package com.example.carez.di

import androidx.room.Room
import com.example.carez.data.local.LocalDatabase
import com.example.carez.data.local.datasource.UserLocalDataSource
import com.example.carez.data.local.datasource.UserLocalDataSourceImpl
import com.example.carez.data.remote.datasource.GoogleAuthDataSource
import com.example.carez.data.remote.datasource.GoogleAuthDataSourceImpl
import com.example.carez.data.remote.datasource.UserRemoteDataSource
import com.example.carez.data.remote.datasource.UserRemoteDataSourceImpl
import com.example.carez.data.repository.UserRepositoryImpl
import com.example.carez.domain.repository.UserRepository
import com.example.carez.domain.usecase.CheckSignInUseCase
import com.example.carez.domain.usecase.CheckUserInfoUseCase
import com.example.carez.domain.usecase.InsertUserUseCase
import com.example.carez.domain.usecase.SignInWithEmailAndPasswordUseCase
import com.example.carez.domain.usecase.SignInWithGoogleUseCase
import com.example.carez.domain.usecase.SignOutUseCase
import com.example.carez.domain.usecase.SignUpWithEmailAndPasswordUseCase
import com.example.carez.domain.usecase.ValidateSignUpInputUseCase
import com.example.carez.presentation.activity.main.MainViewModel
import com.example.carez.presentation.activity.signin.SignInViewModel
import com.example.carez.presentation.activity.signup.SignUpViewModel
import com.example.carez.presentation.activity.splash.SplashViewModel
import com.example.carez.presentation.activity.userinfo.UserInfoViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    // Firebase
    single { FirebaseFirestore.getInstance() }
    single { FirebaseAuth.getInstance() }

    // Database
    single { Room.databaseBuilder(get(), LocalDatabase::class.java, "carez.db").build() }
    single { get<LocalDatabase>().userDao() }

    // DataSource
    single<GoogleAuthDataSource> { GoogleAuthDataSourceImpl() }
    single<UserLocalDataSource> { UserLocalDataSourceImpl(get()) }
    single<UserRemoteDataSource> { UserRemoteDataSourceImpl(get(),get()) }
    // Repository

    single<UserRepository> { UserRepositoryImpl(get(), get(), get()) }

    // UseCase
    factory { SignInWithGoogleUseCase(get()) }
    factory { InsertUserUseCase(get()) }
    factory { CheckSignInUseCase(get()) }
    factory { SignOutUseCase(get()) }
    factory { SignUpWithEmailAndPasswordUseCase(get()) }
    factory { SignInWithEmailAndPasswordUseCase(get()) }
    factory { ValidateSignUpInputUseCase() }
    factory { CheckUserInfoUseCase(get()) }


    // ViewModel
    viewModel {
        SignInViewModel(
            signInWithGoogleUseCase = get(),
            signInWithEmailAndPasswordUseCase = get(),
            insertUserUseCase       = get(),
            firebaseAuth            = get()
        )
    }
    viewModel { MainViewModel(get()) }
    viewModel { SplashViewModel(get(),get(),get()) }
    viewModel { SignUpViewModel(get(),get()) }
    viewModel { UserInfoViewModel(get(), get()) }

}
