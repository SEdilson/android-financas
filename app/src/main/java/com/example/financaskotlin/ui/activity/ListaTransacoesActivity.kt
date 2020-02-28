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
import com.example.financaskotlin.ui.dialog.AlteraTransacaoDialog
import kotlinx.android.synthetic.main.activity_lista_transacoes.*

class ListaTransacoesActivity : AppCompatActivity() {

    private val transacoes: MutableList<Transacao> = mutableListOf()
    private val viewDaActivity: View by lazy {
        window.decorView
    }
    private val viewGroupDaActivity by lazy {
        viewDaActivity as ViewGroup
    }

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
            viewGroupDaActivity
        )
            .chama(tipo, object : TransacaoDelegate {
                override fun delegate(transacao: Transacao) {
                    adiciona(transacao)
                    lista_transacoes_adiciona_menu.close(true)
                }
            })
    }

    private fun adiciona(transacao: Transacao) {
        transacoes.add(transacao)
        atualizaTransacoes()
    }

    private fun atualizaTransacoes() {
        configuraListaDeTransacoes()
        configuraResumoView()
    }

    private fun configuraResumoView() {
        val resumoView = ResumoView(viewDaActivity, transacoes, this)
        resumoView.atualiza()
    }

    private fun configuraListaDeTransacoes() {
        val listaTransacoesAdapter = ListaTransacoesAdapter(transacoes, this)
        with(lista_transacoes_listview) {
            adapter = listaTransacoesAdapter
            setOnItemClickListener { _, _, position, _ ->
                val transacao = transacoes[position]
                chamaDialogDeAlteracao(transacao, position)
            }
        }
    }

    private fun chamaDialogDeAlteracao(transacao: Transacao, position: Int) {
        AlteraTransacaoDialog(this, viewGroupDaActivity)
            .chama(transacao, object : TransacaoDelegate {
                override fun delegate(transacao: Transacao) {
                    atualiza(transacao, position)
                }
            })
    }

    private fun atualiza(transacao: Transacao, position: Int) {
        transacoes[position] = transacao
        atualizaTransacoes()
    }
}