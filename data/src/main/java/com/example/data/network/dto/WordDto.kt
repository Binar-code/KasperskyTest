package com.example.data.network.dto

data class WordDto(
    val id: Long,
    val text: String,
    val meanings: List<MeaningDto>
)
