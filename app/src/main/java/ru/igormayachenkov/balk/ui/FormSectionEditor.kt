package ru.igormayachenkov.balk.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import ru.igormayachenkov.balk.data.Balk
import ru.igormayachenkov.balk.data.FakeData
import ru.igormayachenkov.balk.ui.theme.BalkTheme

@Composable
fun FormSectionEditor(
    balk: Balk,
    onCancel: () -> Unit,
    onSave: (Balk)-> Unit,
) {
    var editorState by rememberSaveable{ mutableStateOf<FormEditorState>(
        FormEditorState.fromForm(balk.form))
    }

    SectionEditor(
        title = "The Balk Form and Size",
        onCancel = onCancel,
        onSave = {
            onSave( editorState.copyToBalk(balk) )
        },
        onSaveEnabled = {
            editorState.isValid
        }
    ) {

        // FORM CLASS
        SectionRow("Class") {
            with(editorState) {
                FormClassDropdownMenu(
                    formClass = formClass,
                    onChange  = {editorState = updateClass(it)}
                )
            }
        }

        // WIDTH
        if(editorState.formClass == FormClass.RECTANGLE) {
            SectionRow("Width") {
                with(editorState) {
                    SectionNumField(
                        text    = widthText,
                        isError = width == null,
                        onChange= { editorState = updateWidth(it) }
                    )
                }
            }
        }

        // HEIGHT
        if(editorState.formClass == FormClass.RECTANGLE) {
            SectionRow("Height") {
                with(editorState) {
                    SectionNumField(
                        text = heightText,
                        isError = height == null,
                        onChange = { editorState = updateHeight(it) }
                    )
                }
            }
        }

        // RADIUS
        if(editorState.formClass == FormClass.CIRCLE) {
            SectionRow("Radius") {
                with(editorState) {
                    SectionNumField(
                        text    = radiusText,
                        isError = radius == null,
                        onChange= { editorState = updateRadius(it) }
                    )
                }
            }
        }

        // LENGTH
        SectionRow("Length") {
            with(editorState) {
                SectionNumField(
                    text    = lengthText,
                    isError = length == null,
                    onChange= { editorState = updateLength(it) }
                )
            }
        }
    }
}

@Composable
private fun FormClassDropdownMenu(
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
        FormSectionEditor(FakeData.balk, {}, {})
    }
}


