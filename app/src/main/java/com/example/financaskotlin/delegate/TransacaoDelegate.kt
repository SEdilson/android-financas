package com.example.financaskotlin.delegate

import com.example.financaskotlin.models.Transacao

interface TransacaoDelegate {
    fun delegate(transacao: Transacao)
}