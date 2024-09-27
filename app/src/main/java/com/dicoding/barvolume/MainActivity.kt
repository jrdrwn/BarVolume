package com.dicoding.barvolume

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.dicoding.barvolume.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {
    companion object {
        private const val STATE_RESULT = "state_result"
    }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCalculate.setOnClickListener(this)

        if (savedInstanceState != null) {
            val result = savedInstanceState.getString(STATE_RESULT)
            binding.tvResult.text = result
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putString(STATE_RESULT, binding.tvResult.text.toString())
    }

    override fun onClick(v: View?) {
        if (v?.id == R.id.btn_calculate) {
            val inputLength = binding.editLength.text.toString().trim()
            val inputWidth = binding.editWidth.text.toString().trim()
            val inputHeight = binding.editHeight.text.toString().trim()
            var isEmptyFields = false
            if (inputLength.isEmpty()) {
                isEmptyFields = true
                binding.editLength.error = "Field ini tidak boleh kosong"
            }
            if (inputWidth.isEmpty()) {
                isEmptyFields = true
                binding.editWidth.error = "Field ini tidak boleh kosong"
            }
            if (inputHeight.isEmpty()) {
                isEmptyFields = true
                binding.editHeight.error = "Field ini tidak boleh kosong"
            }

            if (!isEmptyFields) {
                val volume = inputLength.toDouble() * inputWidth.toDouble() * inputHeight.toDouble()
                binding.tvResult.text = volume.toString()
            }
        }
    }
}