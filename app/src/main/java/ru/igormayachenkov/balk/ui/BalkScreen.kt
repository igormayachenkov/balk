package ru.igormayachenkov.balk.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.igormayachenkov.balk.data.Balk
import ru.igormayachenkov.balk.data.FakeData
import ru.igormayachenkov.balk.domain.Calculation
import ru.igormayachenkov.balk.ui.theme.BalkTheme

@Composable
fun BalkScreen(
    state: BalkUiState,
    updateBalk: (Balk)-> Unit
){
    val (balk,calculation) = state

    var editorState by rememberSaveable{ mutableStateOf<EditorUiState?>(null) }

    Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {

        // TITLE
        Row(Modifier
            .fillMaxWidth()
            .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text("Balk", Modifier.weight(1f), style = MaterialTheme.typography.headlineMedium)
            if(editorState==null)
                Button({ editorState = EditorUiState(balk) }) {
                    Text("Edit")
                }
            else
                Button({ editorState = null }) {
                    Text("Cancel")
                }
        }

        // Width
        Row(Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text("Width", modifier = Modifier.weight(1f), style = MaterialTheme.typography.bodyMedium)

            editorState?.let { editor ->
                OutlinedTextField(
                    value = editor.widthText,
                    singleLine = true,
                    shape = shapes.large,
                    modifier = Modifier.weight(1f),
                    onValueChange = { editorState = editor.updateWidthText(it) },
                    label = {
                        if (editor.width == null)
                            Text("wrong value")
                    },
                    isError = editor.width == null,
                    //                keyboardOptions = KeyboardOptions.Default.copy(
                    //                    imeAction = ImeAction.Done
                    //                ),
                    //                keyboardActions = KeyboardActions(
                    //                    onDone = { onKeyboardDone() }
                    //                )
                )
            }?: run{
                Text("${balk.width}", modifier = Modifier.weight(1f))
            }
        }

        HorizontalDivider(Modifier.padding(vertical = 20.dp))

        editorState?.let { editor ->
            // CALCULATE BUTTON
            Button(
                enabled = editor.isValid,
                onClick = {
                    updateBalk( balk.copy(
                        width = editor.width!!
                    ))
                    editorState=null
                }
            ) {
                Text("Calculate")
            }
        }?:run{
            // CALCULATION
            Text("The balk calculation:", Modifier.padding(bottom = 10.dp))
            CalculationScreen(calculation)

        }
    }
}

//--------------------------------------------------------------------------------------------------
// PREVIEW
@Preview
@Composable
private fun Preview(){
    BalkTheme(darkTheme = true) {
        Surface() {
            BalkScreen(
                BalkUiState(
                    balk = FakeData.balk,
                    calculation = Calculation.Success(0.13)
                ), {}
            )
        }
    }
}

