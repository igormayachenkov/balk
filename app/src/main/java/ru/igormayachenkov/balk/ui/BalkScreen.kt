package ru.igormayachenkov.balk.ui

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
import ru.igormayachenkov.balk.domain.Calculation
import ru.igormayachenkov.balk.ui.theme.BalkTheme

@Composable
fun BalkScreen(
    state: BalkUiState,
    updateBalk: (Balk)-> Unit,
){
    val (balk,calculation) = state

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
        // SHAPE Section
        Column (sectionModifier) {
            var showEditor by rememberSaveable{ mutableStateOf<Boolean>(false) }

            SectionTitle("Shape") {showEditor=true}

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
                Text("${balk.width}", modifier = Modifier.weight(1f))
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
                Text("${balk.height}", modifier = Modifier.weight(1f))
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
                Text("${balk.length}", modifier = Modifier.weight(1f))
            }

            // Shape EDITOR DIALOG
            if(showEditor) ShapeEditor(
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
