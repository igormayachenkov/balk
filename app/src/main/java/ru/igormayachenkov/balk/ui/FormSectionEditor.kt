package ru.igormayachenkov.balk.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.igormayachenkov.balk.data.Balk
import ru.igormayachenkov.balk.data.FakeData
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

    SectionEditor(
        title = "The balk form and size",
        onCancel = onCancel,
        onSave = {
            onSave( formEditorState.copyToBalk(balk) )
        },
        onSaveEnabled = {
            formEditorState.isValid
        }
    ) {

        // FORM CLASS
        SectionRow("Class") {
            with(formEditorState) {
                FormClassDropdownMenu(
                    formClass = formClass,
                    onChange  = {formEditorState = updateClass(it)}
                )
            }
        }

        // WIDTH
        if(formEditorState.formClass == FormClass.RECTANGLE) {
            SectionRow("Width") {
                with(formEditorState) {
                    SectionNumField(
                        text    = widthText,
                        isError = width == null,
                        onChange= { formEditorState = updateWidth(it) }
                    )
                }
            }
        }

        // HEIGHT
        if(formEditorState.formClass == FormClass.RECTANGLE) {
            SectionRow("Height") {
                with(formEditorState) {
                    SectionNumField(
                        text = heightText,
                        isError = height == null,
                        onChange = { formEditorState = updateHeight(it) }
                    )
                }
            }
        }

        // RADIUS
        if(formEditorState.formClass == FormClass.CIRCLE) {
            SectionRow("Radius") {
                with(formEditorState) {
                    SectionNumField(
                        text    = radiusText,
                        isError = radius == null,
                        onChange= { formEditorState = updateRadius(it) }
                    )
                }
            }
        }

        // LENGTH
        SectionRow("Length") {
            with(formEditorState) {
                SectionNumField(
                    text    = lengthText,
                    isError = length == null,
                    onChange= { formEditorState = updateLength(it) }
                )
            }
        }
    }
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


