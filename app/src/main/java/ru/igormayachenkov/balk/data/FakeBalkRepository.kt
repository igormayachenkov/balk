package ru.igormayachenkov.balk.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeBalkRepository : BalkRepository {
    override val data: Flow<Balk> = flow {
        emit(FakeData.balk)
    }
}