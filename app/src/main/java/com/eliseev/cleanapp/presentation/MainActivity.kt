package com.eliseev.cleanapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import com.eliseev.cleanapp.R
import com.eliseev.cleanapp.data.repository.PricePetrolRepositoryImpl
import com.eliseev.cleanapp.databinding.ActivityMainBinding
import com.eliseev.cleanapp.domain.model.PricePetrol
import com.eliseev.cleanapp.domain.usecase.GetHistoryInfo
import com.eliseev.cleanapp.domain.usecase.GetPricePetrol
import com.eliseev.cleanapp.domain.usecase.SavePricePetrol
import com.eliseev.cleanapp.presentation.viewhistoryprices.HistoryPrices
import java.lang.NumberFormatException


class MainActivity : AppCompatActivity() {

    private val userRepository by lazy {
        PricePetrolRepositoryImpl(
            context = applicationContext
        )
    }
    private val savePricePetrolUseCase by lazy {
        SavePricePetrol(
            userRepository = userRepository
        )
    }
    private val getPricePetrolUseCase by lazy {
       GetPricePetrol(
            userRepository = userRepository
        )
    }
    private val getHistoryInfoPetrolUseCase by lazy {
        GetHistoryInfo(
            userRepository = userRepository
        )
    }

    var _binding: ActivityMainBinding? = null
    val binding: ActivityMainBinding
        get() = _binding ?: throw RuntimeException("ActivityMainBinding is null")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()

        binding.receiveButton.setOnClickListener {
            binding.dataTextView.text = getPricePetrolUseCase.execute().value.toString()
        }

        binding.saveButton.setOnClickListener {
            try {
                val price = binding.dataEditText.text.toString().toLong()
                if(savePricePetrolUseCase.execute(PricePetrol(price)))
                    binding.dataTextView.text = getString(R.string.save_successfully)
                else
                    binding.dataTextView.text = getString(R.string.no_saved)
            }catch (e: NumberFormatException) {
                binding.dataTextView.text = getString(R.string.no_saved)
            }
        }

        binding.linearPrices.initHistoryPrices(getHistoryInfoPetrolUseCase.execute())
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}