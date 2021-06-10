package com.krismanpratama.expertsystem.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "rules_basis_pengetahuan",
    foreignKeys = [
        ForeignKey(
            entity = BasisPengetahuanMaster::class,
            parentColumns = ["id"],
            childColumns = ["basis_pengetahuan_master_id"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Gejala::class,
            parentColumns = ["id"],
            childColumns = ["gejala_id"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class RulesBasisPengetahuan(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "basis_pengetahuan_master_id")
    val basisPengetahuanMasterId: Int,

    @ColumnInfo(name = "gejala_id")
    val gejalaId: Int,

    @ColumnInfo(name = "keyakinan")
    val keyakinan: Double
)
