package com.krismanpratama.expertsystem.data.entity

import androidx.room.Embedded
import androidx.room.Relation

data class BasisPengetahuanMasterWithRulesBasisPengetahuan(
    @Embedded val basisPengetahuanMaster: BasisPengetahuanMaster,
    @Relation(
        parentColumn = "id",
        entityColumn = "basis_pengetahuan_master_id"
    )
    val rulesBasisPengetahuan: List<RulesBasisPengetahuan>
)