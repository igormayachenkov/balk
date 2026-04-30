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
import ru.igormayachenkov.balk.data.Support
import ru.igormayachenkov.balk.ui.theme.BalkTheme

@Composable
fun LoadSectionEditor(
    balk: Balk,
    onCancel: () -> Unit,
    onSave: (Balk)-> Unit,
) {
    var editorState by rememberSaveable {
        mutableStateOf<Load>(balk.load)
    }

    SectionEditor(
        title = "The Balk Load",
        onCancel = onCancel,
        onSave = {
            onSave(balk.copy(load = editorState))
        },
        onSaveEnabled = { true }
    ) {

        // SUPPORT CLASS
        SectionRow("Class") {
            with(editorState) {
                LoadClassDropdownMenu(
                    load = editorState,
                    onChange  = {editorState = it}
                )
            }
        }

    }
}

@Composable
private fun LoadClassDropdownMenu(
    load: Load,
    onChange : (Load)->Unit
) {
    var expanded by remember { mutableStateOf(false) }
    Box{
        OutlinedButton({ expanded = !expanded }) {
            Text(load.toString())
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded=false }
        ) {
            DropdownMenuItem(
                text = { Text("Center Point") },
                enabled = load!= Load.CenterPoint,
                onClick = { expanded=false; onChange(Load.CenterPoint) }
            )
            DropdownMenuItem(
                text = { Text("Uniformly Distributed") },
                enabled = load!= Load.Distributed,
                onClick = { expanded=false; onChange(Load.Distributed) }
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
