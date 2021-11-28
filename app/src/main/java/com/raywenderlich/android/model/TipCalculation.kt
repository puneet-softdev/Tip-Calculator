package com.raywenderlich.android.model

data class TipCalculation(
    val bill: Double = 0.0,
    val tipInPercentage: Int = 0,
    val totalAmount: Double = 0.0,
    val tipAmount: Double = 0.0
)