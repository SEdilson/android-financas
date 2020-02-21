package com.example.financaskotlin.extensions

import java.text.SimpleDateFormat
import java.util.*

fun Calendar.formataDataParaPadraoBrasileiro(): String {
    val formatoBrasileiroData = "dd/MM/yyyy"
    val formatacaoData = SimpleDateFormat(formatoBrasileiroData)
    return formatacaoData.format(this.time)
}