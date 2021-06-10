package com.krismanpratama.expertsystem.ui.gejala

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.krismanpratama.expertsystem.data.entity.Gejala
import com.krismanpratama.expertsystem.databinding.ActivityAddGejalaBinding
import com.krismanpratama.expertsystem.helper.ViewModelFactory
import kotlinx.coroutines.InternalCoroutinesApi


class AddGejalaActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_GEJALA = "extra_gejala"
        const val EXTRA_POSITION = "extra_position"
        const val REQUEST_TYPE = "request_type"
        const val EXTRA_GEJALA_ID = "extra_gejala_id"
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
    private var gejala: Gejala? = null
    private var     position = 0

    private lateinit var addGejalaViewModel: AddGejalaViewModel
    private lateinit var activityAddGejalaBinding: ActivityAddGejalaBinding
    private val arrKeyakinan = arrayOf("Pilih Keyakinan","Sangat Yakin","Yakin","Cukup Yakin","Sedikit Yakin","Tidak Tahu","Tidak")
    private var doubKeyakinan: Double? = null
    private lateinit var spinner: Spinner
    @InternalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityAddGejalaBinding = ActivityAddGejalaBinding.inflate(layoutInflater)
        setContentView(activityAddGejalaBinding.root)
        addGejalaViewModel = obtainViewModel(this)

        setupView()

        activityAddGejalaBinding.buttonSave.setOnClickListener {
            with(activityAddGejalaBinding) {
                val namaGejala = editNamaGejala.text.toString().trim()
//                val keyakinan = editKeyakinan.text.toString().trim()

                when {
                    namaGejala.isEmpty() -> editNamaGejala.error =
                        "Nama Gejala tidak boleh kosong"
//                    keyakinan.isEmpty() -> editKeyakinan.error =
//                        "Nilai keyakinan tidak boleh kosong"

                    spinner.selectedItem.toString() == "Pilih Keyakinan" || spinner.selectedItem == null-> {
                        val errorText = spinner.selectedView as TextView
//                        var errorText = spinner.selectedView
                        errorText.error = ""
                        errorText.setTextColor(Color.RED)
                        errorText.text = "Mohon pilih tingkat keyakinan"
                    }
                    else -> {
                        gejala?.let {
                            it.namaGejala = namaGejala
                        }
                        val intent = Intent().apply {
                            putExtra(EXTRA_GEJALA, gejala)
                            putExtra(EXTRA_POSITION, position)
                        }
                        if (requestType() == REQUEST_UPDATE) {
                            addGejalaViewModel.updateGejala(gejala as Gejala)
                            setResult(RESULT_UPDATE, intent)
                            finish()
                        } else {
                            addGejalaViewModel.addGejala(gejala as Gejala)
                            setResult(RESULT_ADD, intent)
                            finish()
                        }
                    }
                }

            }

        }
    }

    @InternalCoroutinesApi
    private fun obtainViewModel(activity: AppCompatActivity): AddGejalaViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory).get(AddGejalaViewModel::class.java)
    }

    private fun setupView() {
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        when (requestType()) {
            REQUEST_READ -> {
                gejala = intent.getParcelableExtra<Gejala>(EXTRA_GEJALA)
                supportActionBar!!.title = gejala?.namaGejala
                addGejalaViewModel.setSelectedGejala(gejala!!.id)
                addGejalaViewModel.getGejala().observe(this, {
                    activityAddGejalaBinding.editNamaGejala.setText(it.namaGejala)
                    setUpSpinner()
                })
            }
            REQUEST_ADD -> {
                gejala = Gejala()
                activityAddGejalaBinding.buttonSave.visibility = View.VISIBLE
                setUpSpinner()
            }
            //Ini update
            else -> {
                gejala = intent.getParcelableExtra<Gejala>(AddGejalaActivity.EXTRA_GEJALA)
                supportActionBar!!.title = "Edit"
                addGejalaViewModel.setSelectedGejala(gejala!!.id)
                addGejalaViewModel.getGejala().observe(this, {
                    activityAddGejalaBinding.editNamaGejala.setText(it.namaGejala)
                })
                setUpSpinner()
                activityAddGejalaBinding.buttonSave.visibility = View.VISIBLE
            }
        }

    }

    private fun setUpSpinner(){
        spinner = activityAddGejalaBinding.mySpinner
        spinner.adapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,arrKeyakinan)
        activityAddGejalaBinding.mySpinner.onItemSelectedListener = object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                when(position){
                    1 -> {
                        doubKeyakinan = 1.0
                    }
                    2 -> {
                        doubKeyakinan = 0.8
                    }
                    3 -> {
                        doubKeyakinan = 0.6
                    }
                    4 ->{
                        doubKeyakinan = 0.4
                    }
                    5 -> {
                        doubKeyakinan = 0.2
                    }
                    6 -> {
                        doubKeyakinan = 0.0
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }
    }


    private fun requestType(): Int {
        return intent.getIntExtra(AddGejalaActivity.REQUEST_TYPE, 0)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }
}





