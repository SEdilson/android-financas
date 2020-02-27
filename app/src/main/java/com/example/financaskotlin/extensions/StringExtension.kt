package com.example.financaskotlin.extensions

import java.text.SimpleDateFormat
import java.util.*

fun String.limitaCategoriaEmAte(quantidadeCaracteres: Int): String {
    if(this.length > quantidadeCaracteres) {
        val caracterInicial = 0
        return "${this.substring(caracterInicial, quantidadeCaracteres)}..."
    }
    return this
}

fun String.converteParaCalendar(): Calendar {
    val formatoBrasileiroDeData = SimpleDateFormat("dd/MM/yyyy")
    val dataConvertida = formatoBrasileiroDeData.parse(this)
    val data = Calendar.getInstance()
    data.time = dataConvertida
    return data
}