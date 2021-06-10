package com.krismanpratama.expertsystem.ui.penyakit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.krismanpratama.expertsystem.R
import com.krismanpratama.expertsystem.data.entity.Penyakit
import com.krismanpratama.expertsystem.databinding.ActivityAddPenyakitBinding
import com.krismanpratama.expertsystem.helper.ViewModelFactory
import kotlinx.coroutines.InternalCoroutinesApi

class AddPenyakitActivity : AppCompatActivity() {

    companion object{
        const val EXTRA_PENYAKIT = "extra_penyakit"
        const val EXTRA_POSITION = "extra_position"
        const val REQUEST_TYPE = "request_type"
        const val EXTRA_PENYAKIT_ID = "extra_penyakit_id"
        const val REQUEST_ADD = 100
        const val RESULT_ADD = 101
        const val REQUEST_READ = 400
        const val REQUEST_UPDATE = 200
        const val RESULT_UPDATE = 201
        const val RESULT_DELETE = 301
        const val ALERT_DIALOG_CLOSE = 10
        const val ALERT_DIALOG_DELETE = 20
    }

    private var isEdit = false
    private var penyakit: Penyakit? = null
    private var position = 0

    private lateinit var addPenyakitViewModel: AddPenyakitViewModel
    private lateinit var activityAddPenyakitBinding: ActivityAddPenyakitBinding

    @InternalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityAddPenyakitBinding = ActivityAddPenyakitBinding.inflate(layoutInflater)
        setContentView(activityAddPenyakitBinding.root)
        addPenyakitViewModel = obtainViewModel(this)

        setupView()
//        penyakit = intent.getParcelableExtra(EXTRA_PENYAKIT)
//        if (penyakit != null) {
//            position = intent.getIntExtra(EXTRA_POSITION, 0)
//            isEdit = true
//        } else {
//            penyakit = Penyakit()
//        }
//
//        val actionBarTitle: String
//        val btnTitle: String
//
//        if (isEdit) {
//            actionBarTitle = getString(R.string.ubah)
//            btnTitle = getString(R.string.update)
//            if (penyakit != null) {
//                penyakit?.let { penyakit ->
//                    activityAddPenyakitBinding.editNamaPenyakit.setText(penyakit.namaPenyakit)
//                    activityAddPenyakitBinding.editPenyebab.setText(penyakit.penyebab)
//                    activityAddPenyakitBinding.editSolusi.setText(penyakit.solusi)
//                }
//            }
//        } else {
//            actionBarTitle = getString(R.string.tambah)
//            btnTitle = getString(R.string.simpan)
//        }
//        supportActionBar?.title = actionBarTitle
//        supportActionBar?.setDisplayHomeAsUpEnabled(true)
//        activityAddPenyakitBinding.buttonSave.text = btnTitle

        activityAddPenyakitBinding.buttonSave.setOnClickListener {
            with(activityAddPenyakitBinding){
                val namaPenyakit = editNamaPenyakit.text.toString().trim()
                val solusi = editSolusi.text.toString().trim()
                val penyebab = editPenyebab.text.toString().trim()

                when{
                    namaPenyakit.isEmpty() -> editNamaPenyakit.error = "Nama Penyakit tidak boleh kosong"
                    solusi.isEmpty() -> editSolusi.error = "Solusi tidak boleh kosong"
                    penyebab.isEmpty() -> editPenyebab.error = "Penyebab tidak boleh kosong"
                    else -> {
                        penyakit?.let {
                            it.namaPenyakit = namaPenyakit
                            it.penyebab = penyebab
                            it.solusi = solusi
                        }
                        val intent = Intent().apply{
                            putExtra(EXTRA_PENYAKIT,penyakit)
                            putExtra(EXTRA_POSITION,position)
                        }
                        if (requestType() == REQUEST_UPDATE){
                            addPenyakitViewModel.updatePenyakit(penyakit as Penyakit)
                            setResult(RESULT_UPDATE,intent)
                            finish()
                        }else{
                            addPenyakitViewModel.addPenyakit(penyakit as Penyakit)
                            setResult(RESULT_ADD,intent)
                            finish()
                        }
                    }
                }

            }

        }
    }

    @InternalCoroutinesApi
    private fun obtainViewModel(activity: AppCompatActivity): AddPenyakitViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory).get(AddPenyakitViewModel::class.java)
    }

    private fun setupView(){
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        when(requestType()){
            REQUEST_READ->{
                penyakit = intent.getParcelableExtra<Penyakit>(EXTRA_PENYAKIT)
                supportActionBar!!.title = penyakit?.namaPenyakit
                addPenyakitViewModel.setSelectedPenyakit(penyakit!!.id)
                addPenyakitViewModel.getPenyakit().observe(this,{
                    activityAddPenyakitBinding.editNamaPenyakit.setText(it.namaPenyakit)
                    activityAddPenyakitBinding.editPenyebab.setText(it.penyebab)
                    activityAddPenyakitBinding.editSolusi.setText(it.solusi)
                })
            }
            REQUEST_ADD -> {
                penyakit = Penyakit()
                activityAddPenyakitBinding.buttonSave.visibility = View.VISIBLE
            }
            //Ini update
            else -> {
                penyakit = intent.getParcelableExtra<Penyakit>(EXTRA_PENYAKIT)
                supportActionBar!!.title = "Edit"
                addPenyakitViewModel.setSelectedPenyakit(penyakit!!.id)
                addPenyakitViewModel.getPenyakit().observe(this,{
                    activityAddPenyakitBinding.editNamaPenyakit.setText(it.namaPenyakit)
                    activityAddPenyakitBinding.editPenyebab.setText(it.penyebab)
                    activityAddPenyakitBinding.editSolusi.setText(it.solusi)
                })
                activityAddPenyakitBinding.buttonSave.visibility = View.VISIBLE
            }
        }

    }

    private fun requestType(): Int {
        return intent.getIntExtra(REQUEST_TYPE, 0)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }



}