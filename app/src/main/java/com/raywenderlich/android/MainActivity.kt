package com.raywenderlich.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.raywenderlich.android.databinding.ActivityMainBinding
import com.raywenderlich.android.viewmodel.TipCalculatorViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var activityMainBinding: ActivityMainBinding
    private lateinit var tempVariable:String
    private lateinit var shubhamVariable: String
    private val tipCalculatorViewModel: TipCalculatorViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        activityMainBinding.etBill.afterTextChanged {
            if(it.toString().isNotBlank()){
                calculateTipAndTotalAmount(billAmount = it.toString(), tipAmountInPercentage = activityMainBinding.etTipPercentage.text.toString())
            }

        }
        activityMainBinding.etTipPercentage.afterTextChanged{
            if(it.toString().isNotBlank()){
                calculateTipAndTotalAmount(billAmount = activityMainBinding.etBill.text.toString(), tipAmountInPercentage = it.toString())
            }
        }

        tipCalculatorViewModel.totalAmountLd.observe(this, {
            activityMainBinding.apply {
                tvTotalAmountValue.text = it.totalAmount.toString()
                tvTipAmountValue.text = it.tipAmount.toString()
            }
        })
    }

    private fun calculateTipAndTotalAmount(billAmount: String, tipAmountInPercentage: String) {
        val bill = if(billAmount.isNotBlank()){
            billAmount.toDouble()
        }else{
            0.0
        }
        val tip = if(tipAmountInPercentage.isNotBlank()){
            tipAmountInPercentage.toInt()
        }else{
            0
        }
        tipCalculatorViewModel.calculateTip(bill, tip)
    }

    private fun EditText.afterTextChanged(afterTextChanged: (Editable?) -> Unit) {
        this.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // no-op
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // no-op
            }

            override fun afterTextChanged(editable: Editable?) {
                afterTextChanged.invoke(editable)
            }
        })
    }
}