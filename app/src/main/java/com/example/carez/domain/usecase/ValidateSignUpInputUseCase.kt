package com.example.carez.domain.usecase

import android.util.Patterns

class ValidateSignUpInputUseCase {
    operator fun invoke(email: String, password: String): Result<Unit> {
        if (email.isBlank()) {
            return Result.failure(Exception("Email không được để trống"))
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return Result.failure(Exception("Email không hợp lệ"))
        }
        if (password.isBlank()) {
            return Result.failure(Exception("Mật khẩu không được để trống"))
        }
        if (password.length < 6) {
            return Result.failure(Exception("Mật khẩu phải từ 6 ký tự trở lên"))
        }
        return Result.success(Unit)
    }
}
