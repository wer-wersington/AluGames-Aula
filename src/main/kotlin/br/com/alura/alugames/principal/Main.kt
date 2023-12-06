package br.com.alura.alugames.principal

import br.com.alura.alugames.modelo.Gamer
import br.com.alura.alugames.modelo.Jogo
import br.com.alura.alugames.servicos.ConsumoApi
import transformarEmIdade
import java.util.*


fun main() {
    val leitura  = Scanner(System.`in`)
    val gamer = Gamer.criarGamer(leitura)
    println("Cadastro concluido com sucesso. Dados do gamer:")
    println(gamer)
    println("Idade do gamer " + gamer.dataNascimento?.transformarEmIdade())//precisa concertar o preenchimento incorreto da idade

    do{
    println("Digite o código do jogo para pesquisar:")

    val busca = leitura.nextLine()
    val buscaApi = ConsumoApi()
    val informacaoJogo = buscaApi.buscaJogo(busca)

    var meuJogo: Jogo? = null


    val resultado = runCatching {

        if (informacaoJogo != null){
        //Esse "if" é ultilizado para verificar se a informação da bucaApi é nula ou não
            meuJogo = Jogo(
                informacaoJogo.info.title,
                informacaoJogo.info.thumb
            )
        }else return //esse return é pra informar o runCatching da falha. Sem ele o código considera onSuccess
    }
    resultado.onFailure {
     println("Jogo não encontrado, tente outro id")
    }

    resultado.onSuccess {
        println("Deseja inserir uma descrição?  S/N")
        val opcao = leitura.nextLine()
        if (opcao.equals("s", true)){
            println("Insira a descricao personalizada para o jogo: ")
            val descricaoPersonalizada = leitura.nextLine()
             meuJogo?.descricao = descricaoPersonalizada
        }else{
            meuJogo?.descricao = meuJogo?.titulo
        }
    gamer.jogosBuscados.add(meuJogo)
    }
        println("Deseja buscar um novo jogo? S/N")
        val resposta = leitura.nextLine()
    } while (resposta.equals("s", true))

    println("Jogos Buscados")
    println(gamer.jogosBuscados)

    println("Jogos ordenados por titulo:")
    gamer.jogosBuscados.sortBy {
        //sortBy organiza a lista pela variavel
        it?.titulo
        //titulo é a variavel que foi organizada por ordem alfabetica
    }
    gamer.jogosBuscados.forEach {
     //forEach tem uma ação para cada objeto, idependente de quantos tenham
        println("Titulo: " + it?.titulo)
        //Imprime somente o titulo de todos os objetos "gamer" criados

    }
    val jogosFiltrados = gamer.jogosBuscados.filter {
        it?.titulo?.contains("Batman", true) ?: false // ignore case é pra ignorar maiusculo
        // "?:" isso é um elvis e serve para retorno nulo
        //contains serve pra saber se tem alguma informação igual na variavel ou no vetor ou na lista
    }
    println("\n Jogos Filtrados: ")
    println(jogosFiltrados)


    println("Deseja excluir algum item da lista original? S/N")
    val opcao = leitura.nextLine()
    if (opcao.equals("s", true)){
        println("\n Lista Atualizada:")
        println(gamer.jogosBuscados)
        println("Informe a posião do jogo que deseja excluir: ")
        val posicao = leitura.nextInt()
        gamer.jogosBuscados.removeAt(posicao)
    }

    println("\n Lista Atualizada:")
    println(gamer.jogosBuscados)

    println("Busca Realizada com sucesso")

}