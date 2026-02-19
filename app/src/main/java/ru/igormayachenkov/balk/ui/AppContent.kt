package ru.igormayachenkov.balk.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.igormayachenkov.balk.data.Balk


@Composable
fun AppContent(modifier: Modifier) {
    Box(modifier = modifier) {
        val balk = Balk(0.05,0.15,3.0)
        BalkScreen(balk)
    }
}