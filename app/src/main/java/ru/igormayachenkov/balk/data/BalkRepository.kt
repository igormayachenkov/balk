package ru.igormayachenkov.balk.data

import kotlinx.coroutines.flow.Flow

interface BalkRepository {
    val data : Flow<Balk>
}