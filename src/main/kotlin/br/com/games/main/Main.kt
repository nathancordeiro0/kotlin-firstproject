package br.com.games.main

import br.com.games.models.Game
import br.com.games.models.Player
import br.com.games.services.ConsumeApi
import br.com.games.utils.turnIntoAge
import java.util.*


fun main() {
    val reading = Scanner(System.`in`)
    val player = Player.createPlayer(reading)
    println("Cadastro concluído com sucesso:")
    println(player)
    println("Idade player: ${player.birthDate?.turnIntoAge()}")

    do {
        println("Digite o código do jogo para buscar:")
        val search = reading.nextLine()

        val searchApi = ConsumeApi()
        val infoGame = searchApi.searchGame(search)

        var myGame: Game? = null

        val result = runCatching {
            myGame = Game(infoGame.info.title, infoGame.info.thumb)
        }

        result.onFailure {
            println("Jogo inexistente. Tente outro id.")
        }

        result.onSuccess {
            println("Deseja inserir uma descrição personalizada? S/N")
            val option = reading.nextLine()

            if (option.equals("s", true)) {
                println("Insira a descrição personalizada para o jogo:")
                val descPersonalized = reading.nextLine()
                myGame?.description = descPersonalized
            } else {
                myGame?.description = myGame?.title
            }

            player.searchedGames.add(myGame)
        }

        println("Deseja buscar uma novo jogo? S/N")
        val option = reading.nextLine()

    } while (option.equals("s", true))

    print("Jogos buscados:")
    println(player.searchedGames)

    println("\n Jogos ordenados por título:")
    player.searchedGames.sortBy {
        it?.title
    }

    player.searchedGames.forEach {
        println("Título: ${it?.title}")
    }

    println("Deseja excluir algum jogo da lista? S/N")
    val option = reading.nextLine()

    if (option.equals("s", true)) {
        print(player.searchedGames)
        println("\n Informe a posição do jogo:")
        val position = reading.nextInt()
        player.searchedGames.removeAt(position)
    }

    println("\n Lista atualizada:")
    println(player.searchedGames)

    println("Buscar finalizada com sucesso")
}
