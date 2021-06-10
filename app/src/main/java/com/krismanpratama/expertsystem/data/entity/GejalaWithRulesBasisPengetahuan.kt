package com.krismanpratama.expertsystem.data.entity

import androidx.room.Embedded
import androidx.room.Relation

data class GejalaWithRulesBasisPengetahuan(
    @Embedded val gejala: Gejala,
    @Relation(
        parentColumn = "id",
        entityColumn = "gejala_id"
    )
    val basisPengetahuan: List<RulesBasisPengetahuan>
)