package com.example.jokebook.DataModel


data class JokeResponse(
    val error: Boolean,
    val category: String,
    val type: String,
    val joke: String,
    val setup: String,
    val delivery: String,
    val flags: Flags,
    val id: Int,
    val safe: Boolean,
    val lang:String
)

data class Flags(
    val nsfw: Boolean,
    val religion: Boolean,
    val political: Boolean,
    val racist: Boolean,
    val sexist: Boolean,
    val explicit:Boolean
)