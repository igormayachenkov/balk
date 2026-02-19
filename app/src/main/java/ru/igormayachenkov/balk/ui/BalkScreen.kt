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
import ru.igormayachenkov.balk.ui.theme.BalkTheme

@Composable
fun BalkScreen(balk: Balk){
    var editMode by rememberSaveable{ mutableStateOf(false) }


    Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {

        // TITLE
        Row(Modifier
            .fillMaxWidth()
            .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text("Balk", Modifier.weight(1f), style = MaterialTheme.typography.headlineMedium)
            if(editMode==false)
                Button({ editMode = true }) {
                    Text("Edit")
                }
            else
                Button({ editMode = false }) {
                    Text("Cancel")
                }
        }

        // Width
        var widthText by rememberSaveable{ mutableStateOf(balk.width.toString()) }
        val widthNew = widthText.toDoubleOrNull()
        Row(Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text("Width", modifier = Modifier.weight(1f), style = MaterialTheme.typography.bodyMedium)

            if(editMode)
                OutlinedTextField(
                    value = widthText,
                    singleLine = true,
                    shape = shapes.large,
                    modifier = Modifier.weight(1f),
                    onValueChange = {widthText=it},
                    label = {
                        if(widthNew==null)
                            Text("wrong value")
                    },
                    isError = widthNew==null,
    //                keyboardOptions = KeyboardOptions.Default.copy(
    //                    imeAction = ImeAction.Done
    //                ),
    //                keyboardActions = KeyboardActions(
    //                    onDone = { onKeyboardDone() }
    //                )
                )
            else
                Text("${balk.width}", modifier = Modifier.weight(1f))
        }

        HorizontalDivider(Modifier.padding(vertical = 20.dp))
        // CALCULATE BUTTON
        if(editMode) {
            Button({ editMode = false }) {
                Text("Calculate")
            }
        }else{
            Text("The balk calculation...")
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
                Balk(
                    width  = 0.05,
                    height = 0.15,
                    length = 3.0
                )
            )
        }
    }
}

