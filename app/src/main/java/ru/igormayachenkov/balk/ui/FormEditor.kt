package ru.igormayachenkov.balk.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import ru.igormayachenkov.balk.data.Balk
import ru.igormayachenkov.balk.data.FakeData
import ru.igormayachenkov.balk.data.Form
import ru.igormayachenkov.balk.ui.theme.BalkTheme

@Composable
fun FormEditor(
    balk: Balk,
    onCancel: () -> Unit,
    onSave: (Balk)-> Unit,
) {
    var formEditorState by rememberSaveable{ mutableStateOf<FormEditorState>(
        FormEditorState.fromForm(balk.form))
    }

    Dialog(onDismissRequest = { onCancel() }) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
        ) {
            Text(
                text = "The balk form and size",
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentSize(Alignment.Center),
                textAlign = TextAlign.Center,
            )

            // FORM CLASS
            Row(Modifier.fillMaxWidth().padding(horizontal = 10.dp), verticalAlignment = Alignment.CenterVertically,) {

                Text("Class", modifier = Modifier.weight(1.5f), style = MaterialTheme.typography.bodyMedium)

                with(formEditorState) {
                    FormClassDropdownMenu(
                        formClass = formClass,
                        onChange  = {formEditorState = updateClass(it)}
                    )
                }
            }

            // WIDTH
            if(formEditorState.formClass == FormClass.RECTANGLE)
            Row(Modifier.fillMaxWidth().padding(horizontal = 10.dp), verticalAlignment = Alignment.CenterVertically,) {

                Text("Width", modifier = Modifier.weight(1.5f), style = MaterialTheme.typography.bodyMedium)

                with(formEditorState) {
                    NumField(
                        text    = widthText,
                        isError = width == null,
                        onChange= { formEditorState = updateWidth(it) }
                    )
                }
            }

            // HEIGHT
            if(formEditorState.formClass == FormClass.RECTANGLE)
            Row(Modifier.fillMaxWidth().padding(horizontal = 10.dp), verticalAlignment = Alignment.CenterVertically,) {

                Text("Height", modifier = Modifier.weight(1.5f), style = MaterialTheme.typography.bodyMedium)

                with(formEditorState) {
                    NumField(
                        text    = heightText,
                        isError = height == null,
                        onChange= { formEditorState = updateHeight(it) }
                    )
                }
            }

            // RADIUS
            if(formEditorState.formClass == FormClass.CIRCLE)
            Row(Modifier.fillMaxWidth().padding(horizontal = 10.dp), verticalAlignment = Alignment.CenterVertically,) {

                Text("Radius", modifier = Modifier.weight(1.5f), style = MaterialTheme.typography.bodyMedium)

                with(formEditorState) {
                    NumField(
                        text    = radiusText,
                        isError = radius == null,
                        onChange= { formEditorState = updateRadius(it) }
                    )
                }
            }

            // LENGTH
            Row(Modifier.fillMaxWidth().padding(horizontal = 10.dp), verticalAlignment = Alignment.CenterVertically,) {

                Text("Length", modifier = Modifier.weight(1.5f), style = MaterialTheme.typography.bodyMedium)

                with(formEditorState) {
                    NumField(
                        text    = lengthText,
                        isError = length == null,
                        onChange= { formEditorState = updateLength(it) }
                    )
                }
            }

            HorizontalDivider(Modifier.padding(vertical = 20.dp))

            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
                OutlinedButton(onCancel) {
                    Text("Cancel")
                }
                Button(
                    onClick = {
                        onSave( formEditorState.copyToBalk(balk) )
                    },
                    enabled = formEditorState.isValid
                ) {
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
        textStyle = TextStyle.Default.copy(textAlign= TextAlign.Center),
        onValueChange = onChange,
        label = {
            if (isError)
                Text("error")
        },
        isError = isError,
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Number
        ),
    )
}

@Composable
fun FormClassDropdownMenu(
    formClass: FormClass,
    onChange : (FormClass)->Unit
) {
    var expanded by remember { mutableStateOf(false) }
    Box{
        OutlinedButton({ expanded = !expanded }) {
            Text(formClass.toString())
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded=false }
        ) {
            DropdownMenuItem(
                text = { Text("Rectangle") },
                enabled = formClass!= FormClass.RECTANGLE,
                onClick = { expanded=false; onChange(FormClass.RECTANGLE) }
            )
            DropdownMenuItem(
                text = { Text("Circle") },
                enabled = formClass!= FormClass.CIRCLE,
                onClick = { expanded=false; onChange(FormClass.CIRCLE)}
            )
        }
    }
}


//--------------------------------------------------------------------------------------------------
// PREVIEW
@Preview(showSystemUi = false)
@Composable
private fun Preview(){
    BalkTheme(darkTheme = true) {
        FormEditor(FakeData.balk, {}, {})
    }
}


