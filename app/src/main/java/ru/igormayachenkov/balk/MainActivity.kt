package ru.igormayachenkov.balk

import android.nfc.Tag
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dagger.hilt.android.AndroidEntryPoint
import ru.igormayachenkov.balk.data.BalkRepository
import ru.igormayachenkov.balk.ui.AppContent
import ru.igormayachenkov.balk.ui.BalkViewModel
import ru.igormayachenkov.balk.ui.theme.BalkTheme
import javax.inject.Inject

private const val TAG = "myapp.MainActivity"

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject lateinit var viewModel : BalkViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate")
        enableEdgeToEdge()
        setContent {
            BalkTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AppContent(
                        modifier = Modifier.padding(innerPadding),
                        viewModel
                    )
                }
            }
        }
    }
}