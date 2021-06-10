package com.krismanpratama.expertsystem.data.entity

import androidx.room.*


@Entity(tableName = "basis_pengetahuan_master",
    foreignKeys = [
    ForeignKey(
        entity = Penyakit::class,
        parentColumns = ["id"],
        childColumns = ["penyakit_id"]
    )
    ],
    indices = [Index(value=["id"]),
                Index(value=["penyakit_id"])])
data class BasisPengetahuanMaster(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name="penyakit_id")
    val penyakitId: Int,

)