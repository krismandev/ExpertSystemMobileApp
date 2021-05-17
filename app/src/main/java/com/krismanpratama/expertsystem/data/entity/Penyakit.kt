package com.krismanpratama.expertsystem.data.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "penyakit")
data class Penyakit(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "nama_penyakit")
    var namaPenyakit: String? = null,

    @ColumnInfo(name = "penyebab")
    var penyebab: String? = null,

    @ColumnInfo(name = "solusi")
    var solusi: String? = null
): Parcelable