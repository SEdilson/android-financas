package com.example.financaskotlin.ui.activity

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.financaskotlin.R
import com.example.financaskotlin.delegate.TransacaoDelegate
import com.example.financaskotlin.models.Tipo
import com.example.financaskotlin.models.Transacao
import com.example.financaskotlin.ui.ResumoView
import com.example.financaskotlin.ui.adapter.ListaTransacoesAdapter
import com.example.financaskotlin.ui.dialog.AdicionaTransacaoDialog
import kotlinx.android.synthetic.main.activity_lista_transacoes.*

class ListaTransacoesActivity : AppCompatActivity() {

    private val transacoes: MutableList<Transacao> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_transacoes)

        configuraResumoView()
        configuraListaDeTransacoes()
        configuraFab()
    }

    private fun configuraFab() {
        lista_transacoes_adiciona_receita.setOnClickListener {
            chamaDialogDeAdicao(Tipo.RECEITA)
        }

        lista_transacoes_adiciona_despesa.setOnClickListener {
            chamaDialogDeAdicao(Tipo.DESPESA)
        }
    }

    private fun chamaDialogDeAdicao(tipo: Tipo) {
        AdicionaTransacaoDialog(
            this,
            window.decorView as ViewGroup
        )
            .chama(tipo, object : TransacaoDelegate {
                override fun delegate(transacao: Transacao) {
                    atualizaTransacoes(transacao)
                    lista_transacoes_adiciona_menu.close(true)
                }
            })
    }

    private fun atualizaTransacoes(transacao: Transacao) {
        transacoes.add(transacao)
        configuraListaDeTransacoes()
        configuraResumoView()
    }

    private fun configuraResumoView() {
        val decorView: View = window.decorView
        val resumoView = ResumoView(decorView, transacoes, this)
        resumoView.atualiza()
    }

    private fun configuraListaDeTransacoes() {
        lista_transacoes_listview.adapter = ListaTransacoesAdapter(transacoes, this)
    }
}