package com.example.contextmonitoring

import android.content.ContentResolver
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
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
import com.example.contextmonitoring.ui.theme.ContextMonitoringTheme
import java.net.URI

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import com.example.contextmonitoring.heartRateCalculator
import java.io.File
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private lateinit var calculateHeartRateButton: Button
    private lateinit var heartRateTextView: TextView
    private lateinit var respiratoryRateButton: Button
    private lateinit var respiratoryRateTextView: TextView
    private lateinit var uploadSymtomsButton: Button

    private var heartRate: Float = 0f
    private var respiratoryRate: Float = 0f

//    private val viewModel: HealthDataViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()

        //MADE CHANGES
        Log.d("CREATION","In the OnCreate Function")
        setContentView(R.layout.activity_main)

        calculateHeartRateButton = findViewById(R.id.heartRateSensor)
        heartRateTextView = findViewById(R.id.heartRateValue)
        respiratoryRateButton = findViewById(R.id.respiratoryRateSensor)
        respiratoryRateTextView = findViewById(R.id.respiratoryRateValue)
        uploadSymtomsButton = findViewById(R.id.uploadSymtomsButton)

        calculateHeartRateButton.setOnClickListener{
            Log.d("CREATION", "on click happened")
            val uri = getUriFromAssets(this, "heartrate.mp4")
            val contentResolver = this.contentResolver
            Log.d("CREATION", uri.toString())
            CoroutineScope(Dispatchers.Main).launch {
                Log.d("CREATION", "calculating heart rate")
                heartRate = withContext(Dispatchers.IO) {
                    heartRateCalculator(uri, contentResolver).toFloat()
                }
                heartRateTextView.text = "Heart Rate: $heartRate bpm"
            }

        }

        respiratoryRateButton.setOnClickListener{
            Log.d("CREATION", "Respiratory Rate Button Clicked")
            val acceX = readCSVFile(this, "CSVBreatheX.csv")
            val acceY = readCSVFile(this, "CSVBreatheY.csv")
            val acceZ = readCSVFile(this, "CSVBreatheZ.csv")

             respiratoryRate = respiratoryRateCalculator(acceX, acceY, acceZ).toFloat()
            respiratoryRateTextView.text = "Respiratory Rate: $respiratoryRate bpm"

        }

        uploadSymtomsButton.setOnClickListener {
            val intent = Intent(this, DiseaseRatingActivity::class.java)
            intent.putExtra("heartRate", heartRate)
            intent.putExtra("respiratoryRate", respiratoryRate)
            startActivity(intent)
        }

//        setContent {
//            ContextMonitoringTheme {
//                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    Greeting(
//                        name = "Hartik",
//                        modifier = Modifier.padding(innerPadding)
//                    )
//                }
//            }
//        }
    }



    fun getUriFromAssets(context: Context, fileName: String): Uri {
        val file = File(context.cacheDir, fileName)
        context.assets.open(fileName).use { inputStream ->
            file.outputStream().use { outputStream ->
                inputStream.copyTo(outputStream)
            }
        }
        return Uri.fromFile(file)
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ContextMonitoringTheme {
        Greeting("Android")
    }
}

