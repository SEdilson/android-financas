package com.example.financaskotlin.ui.activity

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.financaskotlin.R
import com.example.financaskotlin.extensions.formataDataParaPadraoBrasileiro
import com.example.financaskotlin.models.Tipo
import com.example.financaskotlin.models.Transacao
import com.example.financaskotlin.ui.ResumoView
import com.example.financaskotlin.ui.adapter.ListaTransacoesAdapter
import kotlinx.android.synthetic.main.activity_lista_transacoes.*
import kotlinx.android.synthetic.main.form_transacao.*
import kotlinx.android.synthetic.main.form_transacao.view.*
import java.math.BigDecimal
import java.util.*

class ListaTransacoesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_transacoes)

        val transacoes: List<Transacao> = listaDeTransacoes()

        lista_transacoes_adiciona_receita.setOnClickListener {
            val decorView: View = window.decorView
            val viewCriada = LayoutInflater.from(this)
                .inflate(
                    R.layout.form_transacao,
                    decorView as ViewGroup,
                    false
                )

            val ano = 2020
            val mes = 5
            val dia = 20

            val hoje = Calendar.getInstance()
            viewCriada.form_transacao_data.setText(hoje.formataDataParaPadraoBrasileiro())
            viewCriada.form_transacao_data.setOnClickListener {
                DatePickerDialog(this,
                    DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                        val dataSelecionada = Calendar.getInstance()
                        dataSelecionada.set(year, month, dayOfMonth)
                        viewCriada.form_transacao_data
                            .setText(dataSelecionada.formataDataParaPadraoBrasileiro())
                    }, ano, mes, dia
                )
                    .show()
            }

            val adapter = ArrayAdapter.createFromResource(
                this,
                R.array.categorias_de_receita,
                android.R.layout.simple_spinner_dropdown_item
            )
            viewCriada.form_transacao_categoria.adapter = adapter

            AlertDialog.Builder(this)
                .setTitle(R.string.adiciona_receita)
                .setView(viewCriada)
                .setPositiveButton("Adicionar", null)
                .setNegativeButton("Cancelar", null)
                .show()
        }

        configuraResumoView(transacoes)
        configuraListaDeTransacoes(transacoes)
    }

    private fun configuraResumoView(transacoes: List<Transacao>) {
        val decorView: View = window.decorView
        val resumoView = ResumoView(decorView, transacoes, this)
        resumoView.atualiza()
    }

    private fun configuraListaDeTransacoes(transacoes: List<Transacao>) {
        lista_transacoes_listview.adapter = ListaTransacoesAdapter(transacoes, this)
    }

    private fun listaDeTransacoes(): List<Transacao> {
        return listOf(
            Transacao(
                valor = BigDecimal(20.50),
                categoria = "peças da manutenção do carro",
                tipo = Tipo.DESPESA
            ),
            Transacao(
                valor = BigDecimal(300.25),
                categoria = "Economias",
                tipo = Tipo.RECEITA
            ),
            Transacao(
                valor = BigDecimal(120.50),
                tipo = Tipo.RECEITA
            )
        )
    }
}