package com.example.carez.presentation.fragment.progress

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.carez.databinding.FragmentProgressBinding
import com.example.carez.presentation.fragment.ProgressViewModel
import com.example.carez.presentation.state.ProgressState
import com.github.mikephil.charting.components.XAxis
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter

class ProgressFragment : Fragment() {

    private var _binding: FragmentProgressBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ProgressViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProgressBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.loadProgress(10, 2025) // tháng 10 năm 2025 (mock)

        lifecycleScope.launchWhenStarted {
            viewModel.state.collectLatest { state ->
                if (!state.isLoading && state.data.isNotEmpty()) {
                    updateUI(state)
                }
            }
        }
    }

    private fun updateUI(state: ProgressState) {
        val today = state.data.last()
        val percent = (today.calories * 100) / today.goal

        // 🎯 Vòng tròn tiến độ hôm nay
        binding.donutProgress.progress = percent.toFloat()
        binding.donutProgress.text = "$percent%"

        // 🎯 Text kcal
        binding.textKcal.text = "${today.calories} kcal / ${today.goal} kcal"
        binding.textGoal.text = "Hôm nay bạn đạt $percent% mục tiêu"

        // 🎯 Biểu đồ tổng kết 12 tháng
        val monthEntries = mutableListOf<BarEntry>()

        // Giả lập dữ liệu cho 12 tháng, lấy tháng 10 từ state.averagePercent
        for (i in 1..12) {
            val percentMonth = if (i == 10) {
                state.averagePercent.toFloat()
            } else {
                (80..100).random().toFloat()
            }
            monthEntries.add(BarEntry(i.toFloat(), percentMonth))
        }

        val barDataSet = BarDataSet(monthEntries, "Tiến độ (%)").apply {
            color = Color.parseColor("#4A90E2")
            valueTextSize = 12f
        }

        val barData = BarData(barDataSet)
        binding.barChart.data = barData

        val xAxis = binding.barChart.xAxis
        xAxis.granularity = 1f
        xAxis.setLabelCount(12, true)
        xAxis.valueFormatter = IndexAxisValueFormatter(
            (1..12).map { it.toString() } // hiển thị 1 → 12
        )
        xAxis.position = XAxis.XAxisPosition.BOTTOM

        binding.barChart.axisLeft.axisMinimum = 0f
        binding.barChart.axisLeft.axisMaximum = 100f
        binding.barChart.axisRight.isEnabled = false
        binding.barChart.description.isEnabled = false
        binding.barChart.invalidate() // vẽ lại biểu đồ

        // 🎯 Hiển thị 7 ngày (lấy tối đa 7 item cuối)
        val last7 = state.data.takeLast(7)
        val days = listOf(
            binding.day1 to binding.percent1,
            binding.day2 to binding.percent2,
            binding.day3 to binding.percent3,
            binding.day4 to binding.percent4,
            binding.day5 to binding.percent5,
            binding.day6 to binding.percent6,
            binding.day7 to binding.percent7
        )

        for (i in last7.indices) {
            val (dayView, percentView) = days[i]
            val d = last7[i]
            val p = (d.calories * 100) / d.goal
            dayView.text = d.date.substring(5) // hiển thị "MM-dd"
            percentView.text = "$p%"
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
