package com.krismanpratama.expertsystem.ui.gejala

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.krismanpratama.expertsystem.data.entity.Gejala
import com.krismanpratama.expertsystem.databinding.ActivityAddGejalaBinding
import com.krismanpratama.expertsystem.helper.ViewModelFactory
import com.krismanpratama.expertsystem.ui.gejala.AddGejalaActivity
import com.krismanpratama.expertsystem.ui.gejala.AddGejalaViewModel

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
    private var position = 0

    private lateinit var addGejalaViewModel: AddGejalaViewModel
    private lateinit var activityAddGejalaBinding: ActivityAddGejalaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityAddGejalaBinding = ActivityAddGejalaBinding.inflate(layoutInflater)
        setContentView(activityAddGejalaBinding.root)
        addGejalaViewModel = obtainViewModel(this)

        setupView()


        activityAddGejalaBinding.buttonSave.setOnClickListener {
            with(activityAddGejalaBinding) {
                val namaGejala = editNamaGejala.text.toString().trim()
                val keyakinan = editKeyakinan.text.toString().trim()

                when {
                    namaGejala.isEmpty() -> editNamaGejala.error =
                        "Nama Gejala tidak boleh kosong"
                    keyakinan.isEmpty() -> editKeyakinan.error =
                        "Nilai keyakinan tidak boleh kosong"

                    else -> {
                        gejala?.let {
                            it.namaGejala = namaGejala
                            it.keyakinan = keyakinan
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
                    activityAddGejalaBinding.editKeyakinan.setText(it.keyakinan)
                })
            }
            AddGejalaActivity.REQUEST_ADD -> {
                gejala = Gejala()
                activityAddGejalaBinding.buttonSave.visibility = View.VISIBLE
            }
            //Ini update
            else -> {
                gejala = intent.getParcelableExtra<Gejala>(AddGejalaActivity.EXTRA_GEJALA)
                supportActionBar!!.title = "Edit"
                addGejalaViewModel.setSelectedGejala(gejala!!.id)
                addGejalaViewModel.getGejala().observe(this, {
                    activityAddGejalaBinding.editNamaGejala.setText(it.namaGejala)
                    activityAddGejalaBinding.editKeyakinan.setText(it.keyakinan)
                })
                activityAddGejalaBinding.buttonSave.visibility = View.VISIBLE
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





