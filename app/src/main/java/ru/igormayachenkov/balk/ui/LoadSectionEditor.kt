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
import ru.igormayachenkov.balk.data.Load
import ru.igormayachenkov.balk.data.LoadType
import ru.igormayachenkov.balk.data.Support
import ru.igormayachenkov.balk.ui.theme.BalkTheme

@Composable
fun LoadSectionEditor(
    balk: Balk,
    onCancel: () -> Unit,
    onSave: (Balk)-> Unit,
) {
    var editorState by rememberSaveable {
        mutableStateOf<LoadEditorState>(LoadEditorState(balk.load))
    }

    SectionEditor(
        title = "The Balk Load",
        onCancel = onCancel,
    ) {

        // TYPE
        SectionRow {
            SectionLabel("Load type")
            with(editorState) {
                LoadTypeDropdownMenu(
                    loadType = loadType,
                    onChange = {editorState = updateType(it)}
                )
            }
        }

        // WEIGHT
        SectionRow {
            SectionLabel("Weight")
            with(editorState) {
                SectionNumField(
                    text    = weightText,
                    isError = weight == null,
                    onChange= { editorState = updateWeight(it) }
                )
            }
        }


        // BUTTONS
        SectionButtons(
            onCancel = onCancel,
            onSave = { onSave(editorState.copyToBalk(balk)) },
            onSaveEnabled = { editorState.isValid }
        )


    }
}

@Composable
private fun LoadTypeDropdownMenu(
    loadType: LoadType,
    onChange : (LoadType)->Unit
) {
    var expanded by remember { mutableStateOf(false) }
    Box{
        OutlinedButton({ expanded = !expanded }) {
            Text(loadType.toString())
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded=false }
        ) {
            DropdownMenuItem(
                text = { Text("Center Point") },
                enabled = loadType!= LoadType.CenterPoint,
                onClick = { expanded=false; onChange(LoadType.CenterPoint) }
            )
            DropdownMenuItem(
                text = { Text("Uniformly Distributed") },
                enabled = loadType!= LoadType.Distributed,
                onClick = { expanded=false; onChange(LoadType.Distributed) }
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
        LoadSectionEditor(FakeData.balk, {}, {})
    }
}
