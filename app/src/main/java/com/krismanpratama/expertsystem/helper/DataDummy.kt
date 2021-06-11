package com.krismanpratama.expertsystem.helper

import com.krismanpratama.expertsystem.data.entity.Gejala
import com.krismanpratama.expertsystem.data.entity.Penyakit

object DataDummy {

    fun generateDummyPenyakit(): List<Penyakit> {
        val listPenyakit = ArrayList<Penyakit>()
        listPenyakit.add(
            Penyakit(
                1,
                "Berak Kapur",
                "diakibatkan oleh Salmonella pullorum",
                "Pengobatan dapat bisa dilakukan dengan menyuntikan antibiotik, misalnya coccilin, neo terramycin, tetra atau mycomas sesuai dengan dosis yang di tentukan."
            )
        )
        listPenyakit.add(
            Penyakit(
                2,
                "Kolera Ayam",
                "Disebabkan oleh bakteri vibrio",
                "1. Segera pisahkan ayam yang menunjikkan gejala sakit pada kandang isolasi; 2.Segera semprot kandang ayam dengan desinfektan dan segera cuci dan ganti seluruh air minum dan pakan; 3.Bersihkan kotoran dikandang ayam anda dan kubur jauh dari kandang."
            )
        )
        listPenyakit.add(
            Penyakit(
                3,
                "Flu Burung",
                "Flu burung disebabkan oleh infeksi virus influenza tipe A yang berasal dari burung",
                "Cari Di Google"
            )
        )
        listPenyakit.add(
            Penyakit(
                4,
                "Tetelo",
                "disebabkan oleh virus Paramyxo",
                " dapat dilakukan dengan menjaga imunitas ternak melalui kegiatan vaksinasi"
            )
        )
        listPenyakit.add(
            Penyakit(
                5,
                "Tipus Ayam",
                "disebabkan oleh infeksi bakteri Salmonella typhi",
                "Dengan tetra chlor, furox, neo sulfa, furol tetra, nocci, furazon 10, ebs 3, neo terramycin atau suntikan terramycin menurut aturan sesuai pada kemasannya."
            )
        )
        listPenyakit.add(
            Penyakit(
                6,
                "Berak Darah",
                "infeksi protozoa genus Eimeria dari famili Eimeriidae",
                "Kunyit merupakan salah satu tanaman obat-obatan terutama umbi induk yang telah menahun. tanaman ini dapat mengatasi berak darah pada ayam"
            )
        )
        listPenyakit.add(
            Penyakit(
                7,
                "Gumboro",
                " disebabkan oleh virus famili Birnaviridae",
                "Diperlukan desinfektan dengan zat aktif khusus untuk menghancurkan virus Gumboro"
            )
        )
        listPenyakit.add(
            Penyakit(
                8,
                "Salesma Ayam",
                "disebabkan oleh virus influensa Tipe A",
                "Cari Di Google"
            )
        )
        listPenyakit.add(
            Penyakit(
                9,
                "Batuk Ayam Menahun",
                " - ",
                " - "
            )
        )
        listPenyakit.add(
            Penyakit(
                10,
                "Busung Ayam",
                "penyebabnya antara lain pakan (tingginya kadar protein dan kadar garam) dalam ransum, penyakit pernafasan dan suhu lingkungan.",
                "Tidak bisa diobati, tapi dapat dicegah dengan memperbaiki manaejemen pemeliharaan"
            )
        )
        listPenyakit.add(
            Penyakit(
                11,
                "Batuk Darah",
                "adanya sumbatan eksudat dalam trakea",
                "Vaksinasi"
            )
        )
        listPenyakit.add(
            Penyakit(
                12,
                "Mareks",
                "disebabkan oleh virus herpes serotipe 1 yang bersifat limfoproliferatif dan onkogenik",
                "vaksinansi"
            )
        )
        listPenyakit.add(
            Penyakit(
                13,
                "Sindrom Produksi Telur",
                "Penyakit ini disebabkan oleh Hemagglutinating adenovirus",
                "Tidak ada obat yang dapat menyembuhkan penyakit ini, usaha yang dapat dilakukan adalah menjaga kondisi badan tetap baik dan meningkatkan nafsu makan dengan vitamin."
            )
        )
        listPenyakit.add(
            Penyakit(
                14,
                "Produksi Awal",
                "-",
                "-"
            )
        )
        return listPenyakit
    }

    fun generateDummyGejala(): List<Gejala> {
        val listGejala = ArrayList<Gejala>()
        listGejala.add(
            Gejala(
                1,
                "Nafsu Makan Berkurang",
            )
        )

        listGejala.add(
            Gejala(
                2,
                "Nafas sesak/megap-megap",
            )
        )
        listGejala.add(
            Gejala(
                3,
                "Nafas ngorok",
            )
        )
        listGejala.add(
            Gejala(
                4,
                "Nafas cepat",
            )
        )
        listGejala.add(
            Gejala(
                5,
                "Bersin-bersin",
            )
        )
        listGejala.add(
            Gejala(
                6,
                "Batuk",
            )
        )
        listGejala.add(
            Gejala(
                7,
                "Badan Kurus",
            )
        )
        listGejala.add(
            Gejala(
                8,
                "Bulu kusam dan berkerut",
            )
        )
        listGejala.add(
            Gejala(
                9,
                "Diare",
            )
        )
        listGejala.add(
            Gejala(
                10,
                "Produksi telur menurun",
            )
        )
        listGejala.add(
            Gejala(
                11,
                "Kualitas telur jelek",
            )
        )
        listGejala.add(
            Gejala(
                12,
                "Kelihatan ngantuk dan bulu berdiri",
            )
        )
        listGejala.add(
            Gejala(
                13,
                "Kedinginan",
            )
        )
        listGejala.add(
            Gejala(
                14,
                "Tampak lesu",
            )
        )
        listGejala.add(
            Gejala(
                15,
                "Mencret kehijau-hijauan",
            )
        )
        listGejala.add(
            Gejala(
                16,
                "Mencret keputih-putihan",
            )
        )
        listGejala.add(
            Gejala(
                17,
                "Mencret bercampur darah",
            )
        )
        listGejala.add(
            Gejala(
                18,
                "Banyak Minum",
            )
        )
        listGejala.add(
            Gejala(
                19,
                "Muka pucat",
            )
        )
        listGejala.add(
            Gejala(
                20,
                "Nampak membiru",
            )
        )
        listGejala.add(
            Gejala(
                21,
                "Sempoyongan",
            )
        )

        listGejala.add(
            Gejala(
                22,
                "Jengger membengkak merah",
            )
        )
        listGejala.add(
            Gejala(
                23,
                "Jengger pucat",
            )
        )
        listGejala.add(
            Gejala(
                24,
                "Kaki bengkak",
            )
        )
        listGejala.add(
            Gejala(
                25,
                "Kaki meradang/lumpuh",
            )
        )
        listGejala.add(
            Gejala(
                26,
                "Kaki pincang",
            )
        )
        listGejala.add(
            Gejala(
                27,
                "Kelopak mata kemerahan",
            )
        )
        listGejala.add(
            Gejala(
                28,
                "Keluar cairan berbusa dari mata",
            )
        )
        listGejala.add(
            Gejala(
                29,
                "Keluar cairan dari mata dan hidung",
            )
        )
        listGejala.add(
            Gejala(
                30,
                "Keluar nanah dari mata dan bau",
            )
        )
        listGejala.add(
            Gejala(
                31,
                "Kepala bengkak",
            )
        )
        listGejala.add(
            Gejala(
                32,
                "Kepala terputar",
            )
        )
        listGejala.add(
            Gejala(
                33,
                "Mata berair",
            )
        )
        listGejala.add(
            Gejala(
                34,
                "Pembengkakan dari sinus dan mata",
            )
        )
        listGejala.add(
            Gejala(
                35,
                "Perut membesar",
            )
        )
        listGejala.add(
            Gejala(
                36,
                "Sayap menggantung",
            )
        )
        listGejala.add(
            Gejala(
                37,
                "Terdapat kotoran putih menempel disekitar anus",
            )
        )
        listGejala.add(
            Gejala(
                38,
                "Terdapat lendir bercampur darah pada rongga mulut",
            )
        )
        listGejala.add(
            Gejala(
                39,
                "Tidur paruhnya diletakkan di lantai",
            )
        )
        listGejala.add(
            Gejala(
                40,
                "Duduk dengan sikap membungkuk",
            )
        )
        listGejala.add(
            Gejala(
                41,
                "Mati secara mendadak",
            )
        )
        return listGejala
    }

}