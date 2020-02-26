package com.example.financaskotlin.extensions

import java.math.BigDecimal
import java.text.DecimalFormat
import java.util.Locale

fun BigDecimal.formataMoedaParaReal(): String {
    val moedaFormatoBrasileiro = DecimalFormat
        .getCurrencyInstance(Locale("pt", "br"))
    val moedaFormatada = moedaFormatoBrasileiro.format(this)
        .replace("R$", "R$ ")
        .replace("-R$ ", "R$ -")
    return moedaFormatada
}