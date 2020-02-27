package com.example.financaskotlin.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.core.content.ContextCompat
import com.example.financaskotlin.R
import com.example.financaskotlin.extensions.formataDataParaPadraoBrasileiro
import com.example.financaskotlin.extensions.formataMoedaParaReal
import com.example.financaskotlin.extensions.limitaCategoriaEmAte
import com.example.financaskotlin.models.Tipo
import com.example.financaskotlin.models.Transacao
import kotlinx.android.synthetic.main.transacao_item.view.*

class ListaTransacoesAdapter(private val transacoes: List<Transacao>,
                             private val context: Context) : BaseAdapter() {

    private val quantidadeLimiteDaCategoria = 14

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val viewCriada = LayoutInflater.from(context)
            .inflate(R.layout.transacao_item, parent, false)

        val transacao = transacoes[position]

        atribuiIconeATransacao(transacao, viewCriada)
        atribuiCorATransacao(viewCriada, transacao)
        atribuiValorATransacao(viewCriada, transacao)
        atribuiCategoriaATransacao(viewCriada, transacao)
        atribuiDataATransacao(viewCriada, transacao)

        return viewCriada
    }

    private fun atribuiIconeATransacao(transacao: Transacao,
                                       viewCriada: View) {
        val iconeDaTransacao: Int = pegaIconeDaTransacao(transacao.tipo)

        viewCriada.transacao_icone
            .setBackgroundResource(iconeDaTransacao)
    }

    private fun pegaIconeDaTransacao(tipo: Tipo): Int {
        return when (tipo) {
            Tipo.RECEITA -> R.drawable.icone_transacao_item_receita
            Tipo.DESPESA -> R.drawable.icone_transacao_item_despesa
        }
    }

    private fun atribuiCorATransacao(viewCriada: View,
                                     transacao: Transacao) {

        val corDaTransacao: Int = pegaCorDaTransacao(transacao.tipo)

        viewCriada.transacao_valor
            .setTextColor(corDaTransacao)
    }

    private fun pegaCorDaTransacao(tipo: Tipo): Int {
        return when (tipo) {
            Tipo.RECEITA -> ContextCompat.getColor(context, R.color.receita)
            Tipo.DESPESA -> ContextCompat.getColor(context, R.color.despesa)
        }
    }

    private fun atribuiDataATransacao(viewCriada: View,
                                      transacao: Transacao) {
        viewCriada.transacao_data.text = transacao.data.formataDataParaPadraoBrasileiro()
    }

    private fun atribuiCategoriaATransacao(viewCriada: View,
                                           transacao: Transacao) {
        viewCriada.transacao_categoria.text = transacao.categoria
            .limitaCategoriaEmAte(quantidadeLimiteDaCategoria)
    }

    private fun atribuiValorATransacao(viewCriada: View,
                                       transacao: Transacao) {
        viewCriada.transacao_valor.text = transacao.valor.formataMoedaParaReal()
    }

    override fun getItem(position: Int): Transacao {
        return transacoes[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return transacoes.size
    }
}