package com.raywenderlich.android.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.raywenderlich.android.model.TipCalculation
import java.math.RoundingMode

class TipCalculatorViewModel: ViewModel() {
    private val _totalAmountLd: MutableLiveData<TipCalculation> = MutableLiveData()
    val totalAmountLd: LiveData<TipCalculation> = _totalAmountLd

    fun calculateTip(bill: Double, tipInPercentage: Int) {
        val tipAmount = (bill * (tipInPercentage.toDouble() / 100.0))
            .toBigDecimal()
            .setScale(2, RoundingMode.HALF_UP)
            .toDouble()

        val totalAmount = bill + tipAmount

        _totalAmountLd.value = TipCalculation(
            bill= bill,
            tipInPercentage = tipInPercentage,
            tipAmount = tipAmount,
            totalAmount = totalAmount
        )
    }
}