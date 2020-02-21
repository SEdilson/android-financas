package com.example.financaskotlin.models

import java.math.BigDecimal
import java.util.Calendar

class Transacao(val valor: BigDecimal,
                val categoria: String = "Indefinida",
                val tipo: Tipo,
                val data: Calendar = Calendar.getInstance())