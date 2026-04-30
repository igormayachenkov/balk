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
import ru.igormayachenkov.balk.data.Support
import ru.igormayachenkov.balk.ui.theme.BalkTheme

@Composable
fun SupportSectionEditor(
    balk: Balk,
    onCancel: () -> Unit,
    onSave: (Balk)-> Unit,
) {
    var editorState by rememberSaveable {
        mutableStateOf<Support>(balk.support)
    }

    SectionEditor(
        title = "The Balk Support",
        onCancel = onCancel,
        onSave = {
            onSave(balk.copy(support = editorState))
        },
        onSaveEnabled = { true }
    ) {

        // SUPPORT CLASS
        SectionRow("Class") {
            with(editorState) {
                SupportClassDropdownMenu(
                    support = editorState,
                    onChange  = {editorState = it}
                )
            }
        }

    }
}

@Composable
private fun SupportClassDropdownMenu(
    support: Support,
    onChange : (Support)->Unit
) {
    var expanded by remember { mutableStateOf(false) }
    Box{
        OutlinedButton({ expanded = !expanded }) {
            Text(support.toString())
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded=false }
        ) {
            DropdownMenuItem(
                text = { Text("Simply Supported") },
                enabled = support!= Support.SimplySupported,
                onClick = { expanded=false; onChange(Support.SimplySupported) }
            )
            DropdownMenuItem(
                text = { Text("Fixed - Hinged") },
                enabled = support!= Support.FixedHinged,
                onClick = { expanded=false; onChange(Support.FixedHinged) }
            )
            DropdownMenuItem(
                text = { Text("Fixed - Fixed") },
                enabled = support!= Support.FixedFixed,
                onClick = { expanded=false; onChange(Support.FixedFixed) }
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
        SupportSectionEditor(FakeData.balk, {}, {})
    }
}
