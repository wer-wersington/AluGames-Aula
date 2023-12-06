package br.com.alura.alugames.principal

import br.com.alura.alugames.modelo.Gamer

fun main(){
    val gamer1 = Gamer("Jaque","jacque@gmail.com")
    println(gamer1)

    val gamer2 = Gamer("Jeni",
        "jeni@gmal.com",
        "19/19/1992",
        "jeni")

    println(gamer2)


    gamer1.let { it.dataNascimento = "18/09/2000"
        // Esse let ai serve para mudar as propriedades do objeto gamer1
    it.usuario= "jacqueskywlker"
    }.also {
        // Esse also serve para ter outra ação
        println(gamer1.idInterno)
    }
    gamer1.usuario = "Jen"
    println(gamer1)
}