package ru.igormayachenkov.balk.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.igormayachenkov.balk.domain.Calculation

@Composable
fun CalculationScreen(calculation: Calculation){
    Column(Modifier.fillMaxWidth()) {
        when (calculation) {
            Calculation.Progress -> {
                Text("Calculating...")
            }

            is Calculation.Error -> {
                Text("Calculation error")
                Text(calculation.error)
            }

            is Calculation.Success -> {
                Row(Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        "Deflection",
                        modifier = Modifier.weight(1f),
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Text(
                        text = calculation.deflection.toString(),
                        modifier = Modifier.weight(1f),
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }
    }
}