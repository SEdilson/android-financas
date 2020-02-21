package com.example.financaskotlin.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.financaskotlin.R
import com.example.financaskotlin.models.Tipo
import com.example.financaskotlin.models.Transacao
import com.example.financaskotlin.ui.adapter.ListaTransacoesAdapter
import kotlinx.android.synthetic.main.activity_lista_transacoes.*
import java.math.BigDecimal

class ListaTransacoesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_transacoes)

        val transacoes = listOf(
            Transacao(valor = BigDecimal(20.50),
            tipo = Tipo.DESPESA),
            Transacao(valor = BigDecimal(300.25),
                categoria = "Economias",
                tipo = Tipo.RECEITA),
            Transacao(valor = BigDecimal(120.50),
                tipo = Tipo.RECEITA)
        )

        lista_transacoes_listview.adapter = ListaTransacoesAdapter(transacoes, this)
    }

}