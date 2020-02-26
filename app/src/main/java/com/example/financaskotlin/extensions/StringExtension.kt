package com.example.financaskotlin.extensions

fun String.limitaCategoriaEmAte(quantidadeCaracteres: Int): String {
    if(this.length > quantidadeCaracteres) {
        val caracterInicial = 0
        return "${this.substring(caracterInicial, quantidadeCaracteres)}..."
    }
    return this
}