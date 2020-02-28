package com.example.financaskotlin.ui.dialog

import android.app.DatePickerDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.financaskotlin.R
import com.example.financaskotlin.delegate.TransacaoDelegate
import com.example.financaskotlin.extensions.converteParaCalendar
import com.example.financaskotlin.extensions.formataDataParaPadraoBrasileiro
import com.example.financaskotlin.models.Tipo
import com.example.financaskotlin.models.Transacao
import kotlinx.android.synthetic.main.form_transacao.view.*
import java.lang.NumberFormatException
import java.math.BigDecimal
import java.util.*

class AlteraTransacaoDialog(
    private val context: Context,
    viewGroup: ViewGroup) : FormularioTransacaoDialog(context, viewGroup) {

    override val tituloPositiveButton: String
        get() = "Alterar"

    override fun tituloPor(tipo: Tipo): Int {
        if (tipo == Tipo.RECEITA) {
            return R.string.altera_receita
        }
        return R.string.altera_despesa
    }

    fun chama(transacao: Transacao, transacaoDelegate: TransacaoDelegate) {
        val tipo = transacao.tipo
        super.chama(tipo, transacaoDelegate)
        inicializaCampos(transacao, tipo)
    }

    private fun inicializaCampos(transacao: Transacao, tipo: Tipo) {
        inicializaCampoValor(transacao)
        inicializaCampoData(transacao)
        inicializaCampoCategoria(tipo, transacao)
    }

    private fun inicializaCampoCategoria(tipo: Tipo, transacao: Transacao) {
        val categoriasRetornadas = context.resources.getStringArray(categoriasPor(tipo))
        val posicaoCategoria = categoriasRetornadas.indexOf(transacao.categoria)
        campoCategoria.setSelection(posicaoCategoria, true)
    }

    private fun inicializaCampoData(transacao: Transacao) {
        campoData.setText(transacao.data.formataDataParaPadraoBrasileiro())
    }

    private fun inicializaCampoValor(transacao: Transacao) {
        campoValor.setText(transacao.valor.toString())
    }
}