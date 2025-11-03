package com.example.jokebook.DomainModel

data class Joke(
    val id: String,
    val setup: String,
    val punchLine:String,
    val category: String,
    val isTwoPart: Boolean
)
