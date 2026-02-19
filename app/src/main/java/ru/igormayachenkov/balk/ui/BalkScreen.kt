package ru.igormayachenkov.balk.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.OutlinedButton
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.igormayachenkov.balk.data.Balk
import ru.igormayachenkov.balk.data.FakeData
import ru.igormayachenkov.balk.domain.Calculation
import ru.igormayachenkov.balk.ui.theme.BalkTheme

@Composable
fun BalkScreen(
    state: BalkUiState,
    updateBalk: (Balk)-> Unit,
    initialEditorUiState: EditorState?=null
){
    val (balk,calculation) = state

    var editorState by rememberSaveable{ mutableStateOf<EditorState?>(initialEditorUiState) }

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
                Button({ editorState = EditorState(balk) }) {
                    Text("Edit")
                }
            else
                OutlinedButton({ editorState = null }) {
                    Text("Cancel")
                }
        }


        // WIDTH
        Row(Modifier.fillMaxWidth().padding(horizontal = 10.dp), verticalAlignment = Alignment.CenterVertically,) {

            Text("Width", modifier = Modifier.weight(1.5f), style = MaterialTheme.typography.bodyMedium)

            editorState?.let { editor -> with(editor) {
                NumField(
                    text    = widthText,
                    isError = width == null,
                    onChange= { editorState = updateWidth(it) }
                )
            }}?: run{
                Text("${balk.width}", modifier = Modifier.weight(1f))
            }
        }

        // HEIGHT
        Row(Modifier.fillMaxWidth().padding(horizontal = 10.dp), verticalAlignment = Alignment.CenterVertically,) {

            Text("Height", modifier = Modifier.weight(1.5f), style = MaterialTheme.typography.bodyMedium)

            editorState?.let { editor -> with(editor) {
                NumField(
                    text    = heightText,
                    isError = height == null,
                    onChange= { editorState = updateHeight(it) }
                )
            }}?: run{
                Text("${balk.height}", modifier = Modifier.weight(1f))
            }
        }

        // LENGTH
        Row(Modifier.fillMaxWidth().padding(horizontal = 10.dp), verticalAlignment = Alignment.CenterVertically,) {

            Text("Length", modifier = Modifier.weight(1.5f), style = MaterialTheme.typography.bodyMedium)

            editorState?.let { editor -> with(editor) {
                NumField(
                    text    = lengthText,
                    isError = length == null,
                    onChange= { editorState = updateLength(it) }
                )
            }}?: run{
                Text("${balk.length}", modifier = Modifier.weight(1f))
            }
        }

        HorizontalDivider(Modifier.padding(vertical = 20.dp))

        editorState?.let { editor ->
            // CALCULATE BUTTON
            Button(
                enabled = editor.isValid,
                onClick = {
                    updateBalk( editor.copyToBalk(balk) )
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
@Composable
private fun RowScope.NumField(text:String,isError:Boolean,onChange:(String)->Unit){
    OutlinedTextField(
        value = text,
        singleLine = true,
        shape = shapes.medium,
        modifier = Modifier.weight(1f),
        onValueChange = onChange,
        label = {
            if (isError)
                Text("wrong value")
        },
        isError = isError,
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Number
        ),
    )
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

@Preview
@Composable
private fun PreviewEditor(){
    BalkTheme(darkTheme = true) {
        Surface() {
            BalkScreen(
                BalkUiState(
                    balk = FakeData.balk,
                    calculation = Calculation.Success(0.13)
                ), {},
                EditorState(FakeData.balk)
            )
        }
    }
}
