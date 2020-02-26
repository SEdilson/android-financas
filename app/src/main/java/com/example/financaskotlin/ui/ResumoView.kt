package com.example.financaskotlin.ui

import android.content.Context
import android.view.View
import androidx.core.content.ContextCompat
import com.example.financaskotlin.R
import com.example.financaskotlin.extensions.formataMoedaParaReal
import com.example.financaskotlin.models.Resumo
import com.example.financaskotlin.models.Transacao
import kotlinx.android.synthetic.main.resumo_card.view.*
import java.math.BigDecimal

class ResumoView(private val view: View,
                 transacoes: List<Transacao>,
                 context: Context) {

    private val resumo: Resumo = Resumo(transacoes)
    private val corResumoReceita: Int = ContextCompat.getColor(context, R.color.receita)
    private val corResumoDespesa: Int = ContextCompat.getColor(context, R.color.despesa)

    fun atualiza() {
        somaValoresDeDespesa()
        somaValoresDeReceita()
        somaTotal()
    }

    private fun somaValoresDeReceita() {
        val totalReceita = resumo.receita
        with(view.resumo_card_receita) {
            setTextColor(corResumoReceita)
            text = totalReceita.formataMoedaParaReal()
        }
    }

    private fun somaValoresDeDespesa() {
        val totalDespesa = resumo.despesa
        with(view.resumo_card_despesa) {
            setTextColor(corResumoDespesa)
            text = totalDespesa.formataMoedaParaReal()
        }
    }

    private fun somaTotal() {
        val total = resumo.total
        val cor = atribuiCorPeloTipo(total)
        with(view.resumo_card_total) {
            setTextColor(cor)
            text = total.formataMoedaParaReal()
        }
    }

    private fun atribuiCorPeloTipo(valor: BigDecimal): Int {
        if (valor < BigDecimal.ZERO) {
            return corResumoDespesa
        }
        return corResumoReceita
    }
}