package com.example.topic4chap4.dsprefs

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.asLiveData
import com.example.topic4chap4.R
import com.example.topic4chap4.databinding.ActivityMainBinding
import com.example.topic4chap4.databinding.ActivityUserBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class UserActivity : AppCompatActivity() {

    lateinit var binding: ActivityUserBinding
    lateinit var userMan : UserManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userMan = UserManager(this)

        binding.btnSavePres.setOnClickListener {
            val nama = binding.etNama.text.toString()
            val umur = binding.etUmur.text.toString()

            GlobalScope.launch {
                userMan.saveData(nama, umur.toInt())
            }
        }

        binding.btnClearPres.setOnClickListener {
            GlobalScope.launch {
                userMan.deleteData()
            }
        }
    }

        fun setData(){
            userMan.userNama.asLiveData().observe(this){
                binding.resultNama.text = it.toString()
            }
            userMan.userUmur.asLiveData().observe(this, {
                binding.resultUmur.text = it.toString()
            })

        }

}