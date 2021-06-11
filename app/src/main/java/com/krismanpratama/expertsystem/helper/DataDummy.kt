package com.krismanpratama.expertsystem.helper

import com.krismanpratama.expertsystem.data.entity.Gejala
import com.krismanpratama.expertsystem.data.entity.Penyakit

object DataDummy {

    fun generateDummyPenyakit() : List<Penyakit>{
        val listPenyakit = ArrayList<Penyakit>()
        listPenyakit.add(
            Penyakit(
                1,
                "Berak Kapur",
                "Coba Cari di Google",
                "Cari Di Google"
            )
        )

        return listPenyakit
    }

    fun generateDummyGejala() : List<Gejala>{
        val listGejala = ArrayList<Gejala>()
        listGejala.add(
            Gejala(
                1,
                "Nafsu Makan Berkurang",
            )
        )
        return listGejala
    }

}