package br.com.alura.alugames.servicos

import br.com.alura.alugames.modelo.InfoJogo
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

class ConsumoApi {
    fun buscaJogo(id: String): InfoJogo? //Essa "?" serve para indicar que a informação pode ser nula
    {
        val endereco = "https://www.cheapshark.com/api/1.0/games?id=$id"
        val client = HttpClient.newHttpClient()
        val request = HttpRequest.newBuilder()
            .uri(URI.create(endereco))
            .build()

        val response = client
            .send(request, HttpResponse.BodyHandlers.ofString())


        val json = response.body()
        println(json)

    //Quando o Json entrega uma informação com a estrutura errada, o código dá o erro JsonSyntaxException
    try {
        //Esse try testa o código para verificar o erro
        val gson = Gson()
        val meuInfoJogo = gson.fromJson(json, InfoJogo::class.java)
        return meuInfoJogo
    }catch (e: JsonSyntaxException){
        //Esse catch pega o erro de estrutura do Json e retorna uma mensagem de erro
        println("Jogo não encontrado, tente outro id")
    }
    return null
    //Esse null aqui retorna se acontecer o erro, e se der tudo certo ele retorna o meuInfoJogo normalmente
    }
}