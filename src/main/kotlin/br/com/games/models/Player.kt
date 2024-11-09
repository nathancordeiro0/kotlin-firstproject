package br.com.games.models

import java.util.*
import kotlin.random.Random

data class Player(var name: String, var email: String) {
    var birthDate: String? = null

    var user: String? = null
        set(value) {
            field = value
            if (hashId.isNullOrBlank()) {
                createHashId()
            }
        }

    var hashId: String? = null
        private set

    val searchedGames = mutableListOf<Game?>()


    constructor(name: String, email: String, birthDate: String, user: String):
            this(name, email) {
                this.birthDate = birthDate
                this.user = user
                createHashId()
            }

    init {
        if (name.isBlank()) {
            throw IllegalArgumentException("Name cannot be blank")
        }

        this.email = validateEmail()
    }

    override fun toString(): String {
        return "Player(name='$name', email='$email', birthDate=$birthDate, user=$user, hashId=$hashId)"
    }

    fun createHashId() {
        val number = Random.nextInt(10000)
        val tag = String.format("%04d", number)

        hashId = "$user#$tag"
    }

    fun validateEmail(): String {
        val regex = Regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$")
        if (regex.matches(email)) {
            return email
        } else {
            throw IllegalArgumentException("Invalid email format")
        }

    }

    companion object {
        fun createPlayer(reading: Scanner): Player {
            println("Boas vindas ao AluGames! Vamos fazer seu cadastro. Digite seu nome:")
            val name = reading.nextLine()
            println("Digite seu e-mail:")
            val email = reading.nextLine()
            println("Deseja completar seu cadastro com usuário e data de nascimento? (S/N)")
            val option = reading.nextLine()

            if (option.equals("s", true)) {
                println("Digite sua data de nascimento(DD/MM/AAAA):")
                val birth = reading.nextLine()
                println("Digite seu nome de usuário:")
                val user = reading.nextLine()

                return Player(name, email, birth, user)
            } else {
                return Player(name, email)
            }
        }
    }

}
