package com.krismanpratama.expertsystem.data.entity

import androidx.room.Embedded
import androidx.room.Relation

data class PenyakitWithBasisPengetahuan(
    @Embedded val penyakit: Penyakit,
    @Relation(
        parentColumn = "id",
        entityColumn = "penyakit_id"
    )
    val basisPengetahuanMaster: BasisPengetahuanMaster
)