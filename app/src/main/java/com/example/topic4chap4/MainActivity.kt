package com.example.topic4chap4

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.topic4chap4.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    lateinit var sharedPref : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPref = getSharedPreferences("datapref", Context.MODE_PRIVATE)
        saveData()
        viewData()
        clearData()



    }
    fun saveData(){
        binding.btnSave.setOnClickListener {
            val name = binding.etInputName.text.toString()
            val id = binding.etInputId.text.toString()
            val save = sharedPref.edit()
            save.putString("namauser",name)
            save.putString("id",id)
            save.apply()
            Toast.makeText(this,"berhasil menyimpan",Toast.LENGTH_LONG).show()
        }
    }
    fun viewData(){
        binding.btnView.setOnClickListener {
            val getNama = sharedPref.getString("namauser","")
            val getId = sharedPref.getString("id","")

            binding.tvLabelId.text = getId
            binding.tvLabelName.text = getNama

        }
    }
    fun clearData(){
        binding.btnClear.setOnClickListener {
            val hapus = sharedPref.edit()
            hapus.clear()
            hapus.apply()
            binding.tvLabelId.setText("")
            binding.tvLabelName.setText("")
        }
    }
}