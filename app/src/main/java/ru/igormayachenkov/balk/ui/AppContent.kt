package ru.igormayachenkov.balk.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import ru.igormayachenkov.balk.data.Balk
import ru.igormayachenkov.balk.data.FakeBalkRepository
import ru.igormayachenkov.balk.data.InMemoryBalkRepository


@Composable
fun AppContent(modifier: Modifier) {
    Box(modifier = modifier) {

        val balkViewModel = remember { BalkViewModel(InMemoryBalkRepository()) }
        val balkUiState by balkViewModel.state.collectAsState()

        balkUiState?.let {
            BalkScreen(
                state = it,
                updateBalk = balkViewModel::updateBalk
            )
        }
    }
}