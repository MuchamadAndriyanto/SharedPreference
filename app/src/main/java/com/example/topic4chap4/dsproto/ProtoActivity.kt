package com.example.topic4chap4.dsproto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.topic4chap4.R
import com.example.topic4chap4.databinding.ActivityProtoBinding

class ProtoActivity : AppCompatActivity() {
    lateinit var binding: ActivityProtoBinding
    lateinit var protoVm : ProtoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProtoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        protoVm = ViewModelProvider(this).get(ProtoViewModel::class.java)

        //simpan data ke proto
        binding.btnSavePres.setOnClickListener {
            val nama = binding.etNama.text.toString()
            val umur = binding.etUmur.text.toString()
            protoVm.editData(nama, umur.toInt())
        }
        //hapus data
        binding.btnClearPres.setOnClickListener {
            protoVm.deleteData()
        }
        //set ke textview
        protoVm.dataUser.observe(this,{
            binding.resultNama.text = it.nama.toString()
            binding.resultUmur.text = it.umur.toString()
        })
    }
}