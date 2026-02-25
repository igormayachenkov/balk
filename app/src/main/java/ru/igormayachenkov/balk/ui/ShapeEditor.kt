package ru.igormayachenkov.balk.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import ru.igormayachenkov.balk.data.Balk
import ru.igormayachenkov.balk.data.FakeData
import ru.igormayachenkov.balk.ui.theme.BalkTheme

@Composable
fun ShapeEditor(
    balk: Balk,
    onCancel: () -> Unit,
    onSave: (Balk)-> Unit,
) {
    var editorState by rememberSaveable{ mutableStateOf<EditorState>(EditorState(balk)) }

    Dialog(onDismissRequest = { onCancel() }) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
        ) {
            Text(
                text = "The balk shape and size",
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentSize(Alignment.Center),
                textAlign = TextAlign.Center,
            )
            // WIDTH
            Row(Modifier.fillMaxWidth().padding(horizontal = 10.dp), verticalAlignment = Alignment.CenterVertically,) {

                Text("Width", modifier = Modifier.weight(1.5f), style = MaterialTheme.typography.bodyMedium)

                with(editorState) {
                    NumField(
                        text    = widthText,
                        isError = width == null,
                        onChange= { editorState = updateWidth(it) }
                    )
                }
            }

            // HEIGHT
            Row(Modifier.fillMaxWidth().padding(horizontal = 10.dp), verticalAlignment = Alignment.CenterVertically,) {

                Text("Height", modifier = Modifier.weight(1.5f), style = MaterialTheme.typography.bodyMedium)

                with(editorState) {
                    NumField(
                        text    = heightText,
                        isError = height == null,
                        onChange= { editorState = updateHeight(it) }
                    )
                }
            }

            // LENGTH
            Row(Modifier.fillMaxWidth().padding(horizontal = 10.dp), verticalAlignment = Alignment.CenterVertically,) {

                Text("Length", modifier = Modifier.weight(1.5f), style = MaterialTheme.typography.bodyMedium)

                with(editorState) {
                    NumField(
                        text    = lengthText,
                        isError = length == null,
                        onChange= { editorState = updateLength(it) }
                    )
                }
            }

            HorizontalDivider(Modifier.padding(vertical = 20.dp))

            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
                OutlinedButton(onCancel) {
                    Text("Cancel")
                }
                Button({
                    onSave( editorState.copyToBalk(balk) )
                }) {
                    Text("Save")
                }
            }


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
@Preview(showSystemUi = false)
@Composable
private fun Preview(){
    BalkTheme(darkTheme = true) {
        ShapeEditor(FakeData.balk, {}, {})
    }
}


