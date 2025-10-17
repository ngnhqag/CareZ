package com.example.carez.data.repository

import com.example.carez.domain.model.Food
import com.example.carez.domain.repository.FoodRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FoodRepositoryImpl : FoodRepository {

    private val foods: List<Food> = listOf(

// TAB: ĐỒ ĂN NHANH

        Food("fast_01", "Bánh mì trắng (baguette VN)", "Đồ ăn nhanh", 100, 265, 3.2f, 2, 9, 5, 0, 0, "img_banhmitrang", null),
        Food("fast_02", "Bánh mì trứng ốp la", "Đồ ăn nhanh", 100, 230, 11.0f, 1, 10, 3, 0, 0, "img_banhmitrung", null),
        Food("fast_03", "Bánh mì chả lụa", "Đồ ăn nhanh", 100, 250, 9.0f, 1, 11, 3, 1, 0, "img_banhmichalua", null),
        Food("fast_04", "Bánh bao nhân thịt", "Đồ ăn nhanh", 100, 223, 5.0f, 1, 8, 3, 0, 0, "img_banhbaonhanthit", null),
        Food("fast_05", "Bánh cuốn", "Đồ ăn nhanh", 100, 145, 3.8f, 0, 4, 1, 0, 0, "img_banhcuon", null),
        Food("fast_06", "Xôi trắng", "Đồ ăn nhanh", 100, 300, 0.5f, 1, 5, 0, 0, 0, "img_xoitrang", null),
        Food("fast_07", "Xôi đậu xanh", "Đồ ăn nhanh", 100, 320, 2.5f, 3, 8, 1, 0, 0, "img_xoidauxanh", null),
        Food("fast_08", "Bún tươi", "Đồ ăn nhanh", 100, 110, 0.2f, 0, 2, 0, 0, 0, "img_buntuoi", null),
        Food("fast_09", "Bánh phở tươi", "Đồ ăn nhanh", 100, 110, 0.2f, 0, 2, 0, 0, 0, "img_banhphotuoi", null),
        Food("fast_10", "Mì ăn liền (đã nấu)", "Đồ ăn nhanh", 100, 190, 8.0f, 1, 4, 0, 1, 0, "img_mianlien", null),

// TAB: ĐỒ ĂN VẶT
        Food("snack_01", "Khoai lang nướng", "Đồ ăn vặt", 100, 90, 0.1f, 3, 2, 6, 0, 0, "img_khoailangnuong", null),
        Food("snack_02", "Bắp rang bơ", "Đồ ăn vặt", 100, 387, 4.3f, 14, 9, 0, 0, 0, "img_baprangbo", null),
        Food("snack_03", "Bánh tráng trộn", "Đồ ăn vặt", 100, 250, 8.0f, 2, 6, 6, 1, 0, "img_banhtrangtron", null),
        Food("snack_04", "Bánh plan", "Đồ ăn vặt", 100, 143, 4.1f, 0, 5, 15, 0, 0, "img_banhplan", null),
        Food("snack_05", "Bánh chuối chiên", "Đồ ăn vặt", 100, 298, 8.9f, 3, 4, 20, 0, 0, "img_banhchuoichien", null),
        Food("snack_06", "Bánh khoai chiên", "Đồ ăn vặt", 100, 280, 9.2f, 3, 3, 18, 0, 0, "img_banhkhoaichien", null),
        Food("snack_07", "Bánh cam", "Đồ ăn vặt", 100, 340, 7.0f, 2, 6, 22, 0, 0, "img_banhcam", null),
        Food("snack_08", "Bánh đúc", "Đồ ăn vặt", 100, 210, 4.0f, 1, 4, 12, 0, 0, "img_banhduc", null),
        Food("snack_09", "Bánh trôi nước", "Đồ ăn vặt", 100, 247, 6.0f, 1, 5, 15, 0, 0, "img_banhtroinuoc", null),
        Food("snack_10", "Chè đậu xanh", "Đồ ăn vặt", 100, 140, 1.0f, 3, 5, 12, 0, 0, "img_chedauxanh", null),

// TAB: MÓN NẤU CHÍN
        Food("cook_01", "Cá kho tộ", "Món nấu chín", 100, 350, 12.0f, 0, 25, 2, 2, 0, "img_cakhoto", null),
        Food("cook_02", "Thịt kho trứng", "Món nấu chín", 100, 390, 14.0f, 0, 22, 3, 2, 0, "img_thitkhotrung", null),
        Food("cook_03", "Canh rau ngót thịt băm", "Món nấu chín", 100, 87, 3.0f, 1, 6, 1, 1, 0, "img_canhraungotthitbam", null),
        Food("cook_04", "Thịt bò xào hành tây", "Món nấu chín", 100, 210, 8.0f, 0, 19, 3, 1, 0, "img_thitboxaohanhtay", null),
        Food("cook_05", "Cá chiên", "Món nấu chín", 100, 270, 17.0f, 0, 26, 0, 1, 0, "img_cachien", null),
        Food("cook_06", "Tôm rim", "Món nấu chín", 100, 195, 5.0f, 0, 30, 2, 2, 0, "img_tomrim", null),
        Food("cook_07", "Canh chua cá", "Món nấu chín", 100, 115, 3.0f, 1, 12, 2, 2, 0, "img_canhchuaca", null),
        Food("cook_08", "Đậu hũ chiên sả ớt", "Món nấu chín", 100, 180, 10.0f, 1, 10, 2, 1, 0, "img_dauhuchiensaot", null),
        Food("cook_09", "Trứng chiên", "Món nấu chín", 100, 210, 10.0f, 0, 8, 0, 1, 0, "img_trungchien", null),
        Food("cook_10", "Thịt xào rau củ", "Món nấu chín", 100, 185, 6.0f, 2, 15, 3, 1, 0, "img_thitxaoraucu", null),

// TAB: MÓN CHAY
        Food("veg_01", "Cơm chay thập cẩm", "Món chay", 100, 340, 7.0f, 3, 10, 5, 1, 0, "img_comchaythapcam", null),
        Food("veg_02", "Đậu hũ sốt cà", "Món chay", 100, 160, 6.0f, 2, 10, 2, 1, 0, "img_dauhusotca", null),
        Food("veg_03", "Rau củ xào chay", "Món chay", 100, 120, 4.0f, 3, 5, 2, 1, 0, "img_raucuxaochay", null),
        Food("veg_04", "Canh bí đỏ chay", "Món chay", 100, 80, 1.0f, 2, 3, 1, 1, 0, "img_canhbidochay", null),
        Food("veg_05", "Nấm xào đậu hũ", "Món chay", 100, 190, 9.0f, 2, 12, 3, 1, 0, "img_namxaodauhu", null),
        Food("veg_06", "Miến xào chay", "Món chay", 100, 250, 8.0f, 2, 7, 4, 1, 0, "img_mienxaochay", null),
        Food("veg_07", "Bánh cuốn chay", "Món chay", 100, 150, 3.0f, 1, 4, 1, 1, 0, "img_banhcuonchay", null),
        Food("veg_08", "Bún riêu chay", "Món chay", 100, 200, 5.0f, 1, 7, 3, 1, 0, "img_bunrieuchay", null),
        Food("veg_09", "Đậu hũ kho nấm rơm", "Món chay", 100, 180, 7.0f, 2, 11, 2, 1, 0, "img_dauhukhonamrom", null),
        Food("veg_10", "Cà tím kho tộ chay", "Món chay", 100, 160, 6.0f, 3, 8, 3, 1, 0, "img_catimkhotochay", null),

// TAB: THỰC PHẨM TƯƠI SỐNG
        Food("raw_01", "Thịt heo nạc (sống)", "Thực phẩm tươi sống", 100, 143, 3.5f, 0, 26, 0, 0, 0, "img_thitheonac", null),
        Food("raw_02", "Thịt bò thăn (sống)", "Thực phẩm tươi sống", 100, 187, 12.0f, 0, 18, 0, 0, 0, "img_thitbothan", null),
        Food("raw_03", "Cá basa phi lê (sống)", "Thực phẩm tươi sống", 100, 120, 3.0f, 0, 22, 0, 0, 0, "img_cabasaphile", null),
        Food("raw_04", "Cá rô phi (sống)", "Thực phẩm tươi sống", 100, 96, 2.0f, 0, 20, 0, 0, 0, "img_carophi", null),
        Food("raw_05", "Tôm sú (sống)", "Thực phẩm tươi sống", 100, 99, 1.0f, 0, 21, 0, 0, 0, "img_tomsu", null),
        Food("raw_06", "Rau muống (tươi)", "Thực phẩm tươi sống", 100, 35, 0.2f, 3, 3, 1, 0, 0, "img_raumuong", null),
        Food("raw_07", "Cải bó xôi (tươi)", "Thực phẩm tươi sống", 100, 23, 0.4f, 2, 3, 0, 0, 0, "img_caiboxoi", null),
        Food("raw_08", "Cà chua (tươi)", "Thực phẩm tươi sống", 100, 18, 0.2f, 1, 1, 3, 0, 0, "img_cachua", null),
        Food("raw_09", "Cà rốt (tươi)", "Thực phẩm tươi sống", 100, 41, 0.2f, 2, 1, 5, 0, 0, "img_carot", null),
        Food("raw_10", "Chuối (tươi)", "Thực phẩm tươi sống", 100, 89, 0.3f, 2, 1, 12, 0, 0, "img_chuoi", null),

// TAB: ĐỒ UỐNG
        Food("drink_01", "Nước lọc", "Đồ uống", 100, 0, 0.0f, 0, 0, 0, 0, 100, "img_nuocloc", null),
        Food("drink_02", "Nước cam ép (100%)", "Đồ uống", 100, 45, 0.1f, 0, 0, 9, 0, 88, "img_nuoccamep", null),
        Food("drink_03", "Trà xanh (không đường)", "Đồ uống", 100, 1, 0.0f, 0, 0, 0, 0, 99, "img_traxanh", null),
        Food("drink_04", "Cà phê đen (không đường)", "Đồ uống", 100, 2, 0.0f, 0, 0, 0, 0, 99, "img_capheden", null),
        Food("drink_05", "Sữa tươi không đường", "Đồ uống", 100, 61, 3.3f, 0, 3, 5, 0, 88, "img_suatuoikhongduong", null),
        Food("drink_06", "Sữa tươi có đường", "Đồ uống", 100, 66, 3.3f, 0, 3, 6, 0, 88, "img_suatuoicoduong", null),
        Food("drink_07", "Sữa đậu nành (không đường)", "Đồ uống", 100, 33, 1.8f, 0, 2, 1, 0, 94, "img_suadaunanhkhongduong", null),
        Food("drink_08", "Nước dừa tươi", "Đồ uống", 100, 19, 0.2f, 0, 0, 3, 0, 95, "img_nuocduatuoi", null),
        Food("drink_09", "Nước chanh (không đường)", "Đồ uống", 100, 6, 0.1f, 0, 0, 1, 0, 98, "img_nuocchanh", null),
        Food("drink_10", "Sữa chua uống", "Đồ uống", 100, 73, 1.5f, 0, 3, 10, 0, 84, "img_suachuauong", null)
    )

    override fun getFoodsByCategory(category: String): Flow<List<Food>> = flow {
        emit(foods.filter { it.category == category })
    }
}
