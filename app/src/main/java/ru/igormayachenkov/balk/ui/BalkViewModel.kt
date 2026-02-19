package ru.igormayachenkov.balk.ui

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import ru.igormayachenkov.balk.data.Balk
import ru.igormayachenkov.balk.data.BalkRepository
import ru.igormayachenkov.balk.domain.CalculateUseCase
import ru.igormayachenkov.balk.domain.Calculation

data class BalkUiState(
    val balk: Balk,
    val calculation: Calculation
)

class BalkViewModel(
    val repository: BalkRepository
) {
    val scope = CoroutineScope(Dispatchers.Default)

    // DATA
    val state : StateFlow<BalkUiState?> = repository.data.map { balk ->
        BalkUiState(
            balk = balk,
            calculation = CalculateUseCase(balk)()
        )
    }.stateIn(
        scope = scope,
        started = SharingStarted.Eagerly,
        initialValue = null
    )

    // ACTIONS

}