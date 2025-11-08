package com.example.carez.domain.usecase

class CalculateBMR {
    operator fun invoke(gender: String, weight: Float, height: Float, age: Int): Float {
        return if (gender == "Male") {
            66.47f + (13.75f * weight) + (5.003f * height) - (6.755f * age)
        } else {
            655.1f + (9.563f * weight) + (1.85f * height) - (4.676f * age)
        }
    }
}