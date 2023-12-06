package br.com.alura.alugames.modelo

import java.lang.IllegalArgumentException
import java.util.Random
import java.util.Scanner

data class Gamer(var nome: String,
    var email:String){

    var dataNascimento: String? = null
    var usuario: String? = null
        set(value) {
            field = value
            if (idInterno.isNullOrBlank()){
                criarIdInterno()
            }
        }

    var idInterno: String? = null
        private set
    val jogosBuscados = mutableListOf<Jogo?>()

//    init {
//        this.email = validarEmail()
//        // Esse this.email aqui, está usando o validarEmail para validar o valor da variavel email
//        if (nome.isNullOrBlank()){
//            throw IllegalArgumentException("O nome está em branco")
//        }
//    }

    constructor(nome: String,email: String, dataNascimento:String, usuario:String):this(nome,email){
        this.dataNascimento = dataNascimento
        this.usuario = usuario
        criarIdInterno()
    }

    override fun toString(): String {
        return "Gamer(nome='$nome'\n, email='$email'\n, dataNascimento=$dataNascimento\n, usuario=$usuario\n, idInterno=$idInterno)"
    }
    fun criarIdInterno(){
        var numero = kotlin.random.Random.nextInt(10000)
        val tag = String.format("%04d", numero)

        idInterno = "$usuario#$tag"
    }
    fun validarEmail(): String {
        val regex = Regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$")
        if(regex.matches(email)){
            return email
        }else{
    throw IllegalArgumentException("Email invalido")
        }
    }

    companion object {
        fun criarGamer(leitura: Scanner): Gamer{
            println("Boas vindas ao AluGames! Vamos fazer seu cadastro. Digite seu nome:")
            val nome = leitura.nextLine()
            println("Digite seu e-mail:")
            val email = leitura.nextLine()
            println("Deseja completar seu cadastro com usuário e data de nascimento? (S/N)")
            val opcao = leitura.nextLine()

            if (opcao.equals("s", true)){
                println("Digite sua data de nascimento(DD/MM/AAAA):")
                val nascimento = leitura.nextLine()
                println("Digite seu nome de usuário:")
                val usuario = leitura.nextLine()
                return  Gamer(nome, email, nascimento, usuario)
            }else {
                return Gamer(nome, email)
            }
        }
    }
}
