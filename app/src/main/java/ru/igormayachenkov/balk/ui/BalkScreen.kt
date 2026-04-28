package ru.igormayachenkov.balk.ui

import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.igormayachenkov.balk.R
import ru.igormayachenkov.balk.data.Balk
import ru.igormayachenkov.balk.data.FakeData
import ru.igormayachenkov.balk.data.Calculation
import ru.igormayachenkov.balk.ui.theme.BalkTheme
import kotlinx.serialization.json.Json

@Composable
fun BalkScreen(
    state: BalkUiState,
    updateBalk: (Balk)-> Unit,
){
    val (balk,calculation) = state

    Log.w("myapp.BalkScreen","balk: ${Json.encodeToString(balk)}")

    val sectionModifier = Modifier
        .fillMaxWidth()
        .padding(3.dp)
        .border(1.dp, color = MaterialTheme.colorScheme.onSurface, RoundedCornerShape(10.dp))
        .padding(3.dp)

    Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {

        // TITLE
        Row(Modifier
            .fillMaxWidth()
            .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text("Balk Calculator", Modifier.weight(1f), style = MaterialTheme.typography.headlineMedium)
        }

        //------------------------------------------------------------------------------------------
        // FORM Section
        Column (sectionModifier) {
            val (width,height,length) = balk.form as ru.igormayachenkov.balk.data.Form.Rectangle
            var showEditor by rememberSaveable{ mutableStateOf<Boolean>(false) }

            SectionTitle("Form") {showEditor=true}

            // Width
            Row(
                Modifier.fillMaxWidth().padding(horizontal = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    "Width",
                    modifier = Modifier.weight(1.5f),
                    style = MaterialTheme.typography.bodyMedium
                )
                Text("${width}", modifier = Modifier.weight(1f))
            }

            // Height
            Row(
                Modifier.fillMaxWidth().padding(horizontal = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    "Height",
                    modifier = Modifier.weight(1.5f),
                    style = MaterialTheme.typography.bodyMedium
                )
                Text("${height}", modifier = Modifier.weight(1f))
            }

            // Length
            Row(
                Modifier.fillMaxWidth().padding(horizontal = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    "Length",
                    modifier = Modifier.weight(1.5f),
                    style = MaterialTheme.typography.bodyMedium
                )
                Text("${length}", modifier = Modifier.weight(1f))
            }

            // Shape EDITOR DIALOG
            if(showEditor) FormEditor(
                balk = balk,
                onCancel = {showEditor=false},
                onSave = {balk->
                    showEditor=false
                    updateBalk(balk)
                },
            )
        }

        //------------------------------------------------------------------------------------------
        // SUPPORT SECTION
        Column(sectionModifier) {
            var showEditor by rememberSaveable{ mutableStateOf<Boolean>(false) }

            SectionTitle("Support") {showEditor=true}

            Row(
                Modifier.fillMaxWidth().padding(horizontal = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    "Type",
                    modifier = Modifier.weight(1f),
                    style = MaterialTheme.typography.bodyMedium
                )
                Text("${balk.support}", modifier = Modifier.weight(1.5f))
            }
        }

        //------------------------------------------------------------------------------------------
        // LOAD Section
        Column(sectionModifier) {
            var showEditor by rememberSaveable{ mutableStateOf<Boolean>(false) }

            SectionTitle("Load") {showEditor=true}

            Row(
                Modifier.fillMaxWidth().padding(horizontal = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    "Load",
                    modifier = Modifier.weight(1f),
                    style = MaterialTheme.typography.bodyMedium
                )
                Text("${balk.load}", modifier = Modifier.weight(1.5f))
            }
        }

        HorizontalDivider(Modifier.padding(vertical = 20.dp))

        // CALCULATION
        Text("The balk calculation:", Modifier.padding(bottom = 10.dp))
        CalculationScreen(calculation)
    }
}
@Composable
private fun SectionTitle(text:String,onEdit:()->Unit){
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
        Text(text, Modifier.padding(start = 10.dp))
        IconButton(onEdit) { Icon(painterResource(R.drawable.edit),"Edit") }
    }
    HorizontalDivider()
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
