package br.com.games.utils

import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter

fun String.turnIntoAge(): Int {
    val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    var age = 0
    val birthDate = LocalDate.parse(this, formatter)
    val today = LocalDate.now()
    age = Period.between(birthDate, today).years

    return age
}