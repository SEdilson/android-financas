package com.example.financaskotlin.ui.dialog

import android.content.Context
import android.view.ViewGroup
import com.example.financaskotlin.R
import com.example.financaskotlin.models.Tipo

class AdicionaTransacaoDialog(
    context: Context,
    viewGroup: ViewGroup) : FormularioTransacaoDialog(context, viewGroup) {

    override val tituloPositiveButton: String
        get() = "Adicionar"

    override fun tituloPor(tipo: Tipo): Int {
        if (tipo == Tipo.RECEITA) {
            return R.string.adiciona_receita
        }
        return R.string.adiciona_despesa
    }

}