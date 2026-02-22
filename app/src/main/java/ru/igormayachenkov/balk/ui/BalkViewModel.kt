package ru.igormayachenkov.balk.ui

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.transform
import kotlinx.coroutines.launch
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
    val state : StateFlow<BalkUiState?> = repository.data.transform { balk ->
        // Set Progress state
        emit(BalkUiState(
            balk = balk,
            calculation = Calculation.Progress
        ))

        // Calculate
        delay(2_000)
        val calculation = CalculateUseCase()(balk)

        // Set the result state
        emit(BalkUiState(
            balk = balk,
            calculation = calculation
        ))
    }.stateIn(
        scope = scope,
        started = SharingStarted.Eagerly,
        initialValue = null
    )

    // ACTIONS
    fun updateBalk(newBalk: Balk){
        scope.launch {
            repository.updateBalk(newBalk)
        }
    }

}