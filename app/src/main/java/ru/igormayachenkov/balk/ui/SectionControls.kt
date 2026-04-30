package ru.igormayachenkov.balk.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import ru.igormayachenkov.balk.ui.theme.BalkTheme

@Composable
fun SectionEditor(
    title: String,
    onCancel: () -> Unit,
    content: @Composable ColumnScope.() -> Unit
){
    Dialog(onDismissRequest = onCancel) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
        ) {
            // TITLE
            Text(
                text = title,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentSize(Alignment.Center),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleMedium
            )
            HorizontalDivider(Modifier.padding(bottom = 20.dp))

            // CONTENT
            content()

            Spacer(Modifier.height(10.dp))
        }
    }
}
@Composable
fun SectionButtons(
    onCancel: () -> Unit,
    onSave  : (()->Unit)?,
    onSaveEnabled  : ()-> Boolean,
){
    HorizontalDivider(Modifier.padding(vertical = 20.dp))

    // BUTTONS
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
        OutlinedButton(onCancel) {
            Text("Cancel")
        }
        onSave?.let {
            Button(
                onClick = it,
                enabled = onSaveEnabled()
            ) {
                Text("Save")
            }
        }
    }
}

@Composable
fun SectionRow(content: @Composable (RowScope.() -> Unit)) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        content = content
    )
}

@Composable
fun RowScope.SectionLabel(label:String) {
     Text(label, modifier = Modifier.weight(1.5f), style = MaterialTheme.typography.bodyMedium)
}

@Composable
fun RowScope.SectionNumField(text:String, isError:Boolean, onChange:(String)->Unit){
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
fun SectionOption(
    text : String,
    isChecked: Boolean,
    onClick : ()-> Unit
){
    SectionRow {
        Box(Modifier.width(30.dp)){
            if(isChecked) Icon(painterResource(android.R.drawable.star_on),"checked")
        }
        OutlinedButton(onClick, modifier = Modifier.fillMaxWidth()) {
            Text(text)
        }
    }

}

//--------------------------------------------------------------------------------------------------
// PREVIEW
@Preview(showSystemUi = false)
@Composable
private fun SectionEditorPreview(){
    BalkTheme(darkTheme = true) {
        SectionEditor( "Title",{}){
            SectionRow {
                SectionLabel("Num field #1")
                SectionNumField("13", false, {})
            }
            SectionButtons({}, {}, {true})
        }
    }
}
@Preview(showSystemUi = false)
@Composable
private fun OptionsEditorPreview(){
    BalkTheme(darkTheme = true) {
        SectionEditor( "Title",{}){
            SectionOption("Option One", true,{})
            SectionOption("Option Two", false,{})
        }
    }
}
