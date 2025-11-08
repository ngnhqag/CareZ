package com.example.carez.domain.usecase

class CalculateCalories {

    /**
     * @param isDistanceType: true nếu bài tập theo quãng đường, false nếu theo thời gian
     * @param minutes: thời gian tập (phút)
     * @param distance: quãng đường (km), nếu không dùng distance để 0
     * @param lvl: "beginner" hoặc "pro"
     * @param metBeginner: MET beginner
     * @param metPro: MET pro
     * @param bmr: giá trị BMR từ UseCase CalculateBMR
     */

    operator fun invoke( isDistanceType: Boolean, timeMinutes: Int, distance: Float, timeMin: Int, lvl: String, metBeginner: Float
                         , metPro: Float, bmr: Float, gender: String, weight: Float, height: Float, age: Int ): Int {
        val bmr = calculateBMR(gender, weight, height, age)
        var bmrPerMin = bmr / (24*60)

        if ( isDistanceType == false ) {
            if ( lvl == "beginner") {

            }
        }
    }
}