package br.com.games.models

data class Game(val title: String, val thumb: String) {
    var description: String? = null

    override fun toString(): String {
        return "Game:\n" +
                "Title: $title \n" +
                "Thumb: $thumb \n" +
                "Description: $description \n"
    }

}