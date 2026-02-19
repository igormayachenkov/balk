package ru.igormayachenkov.balk.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class InMemoryBalkRepository : BalkRepository {
    private val _data = MutableStateFlow<Balk>(FakeData.balk)
    override val data: Flow<Balk> = _data.asStateFlow()

    override suspend fun updateBalk(balk: Balk) {
        _data.value = balk
    }
}