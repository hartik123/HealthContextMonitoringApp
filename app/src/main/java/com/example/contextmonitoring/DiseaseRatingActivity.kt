package com.example.contextmonitoring

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DiseaseRatingActivity: AppCompatActivity() {
    private lateinit var diseaseRecyclerView: RecyclerView
    private lateinit var submitAllRatingsButton: Button
    private lateinit var diseaseList: List<DiseaseRating>
    private var heartRate: Float = 0f
    private var respiratoryRate: Float = 0f

    private lateinit var viewModel: HealthDataViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_disease_rating)

        heartRate = intent.getFloatExtra("heartRate", 0f)
        respiratoryRate = intent.getFloatExtra("respiratoryRate", 0f)
//        Log.d("CREATION", "HEART RATE $heartRate, RESP RATE $respiratoryRate")
        // Initialize the RecyclerView and Button
        diseaseRecyclerView = findViewById(R.id.diseaseRecyclerView)
        submitAllRatingsButton = findViewById(R.id.submitAllRatingsButton)

        // Create a list of diseases
        diseaseList = listOf(
            DiseaseRating("Nausea", 0f),
            DiseaseRating("Headache", 0f),
            DiseaseRating("Diarrhea", 0f),
            DiseaseRating("Soar Throat", 0f),
            DiseaseRating("Fever", 0f),
            DiseaseRating("Muscle Ache", 0f),
            DiseaseRating("Loss of Smell or Taste", 0f),
            DiseaseRating("Cough", 0f),
            DiseaseRating("Shortness of Breath", 0f),
            DiseaseRating("Feeling Tired", 0f)
        )

        // Set up RecyclerView with the adapter
        diseaseRecyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = DiseaseRatingAdapter(this, diseaseList)
        diseaseRecyclerView.adapter = adapter

        // Initialize ViewModel
        viewModel = ViewModelProvider(this).get(HealthDataViewModel::class.java)

        // Handle the submit button click
        submitAllRatingsButton.setOnClickListener {
            val ratingsSummary = diseaseList.joinToString("\n") {
                "${it.diseaseName}: ${it.rating} stars"
            }
            saveDiseaseRatings()
            Toast.makeText(this, ratingsSummary, Toast.LENGTH_LONG).show()
        }
    }

    private fun saveDiseaseRatings() {
        val nausea = diseaseList.find { it.diseaseName == "Nausea" }?.rating ?: 0f
        val headache = diseaseList.find { it.diseaseName == "Headache" }?.rating ?: 0f
        val diarrhea = diseaseList.find { it.diseaseName == "Diarrhea" }?.rating ?: 0f
        val soreThroat = diseaseList.find { it.diseaseName == "Soar Throat" }?.rating ?: 0f
        val fever = diseaseList.find { it.diseaseName == "Fever" }?.rating ?: 0f
        val muscleAche = diseaseList.find { it.diseaseName == "Muscle Ache" }?.rating ?: 0f
        val lossOfSmellOrTaste =
            diseaseList.find { it.diseaseName == "Loss of Smell or Taste" }?.rating ?: 0f
        val cough = diseaseList.find { it.diseaseName == "Cough" }?.rating ?: 0f
        val shortnessOfBreath =
            diseaseList.find { it.diseaseName == "Shortness of Breath" }?.rating ?: 0f
        val feelingTired = diseaseList.find { it.diseaseName == "Feeling Tired" }?.rating ?: 0f

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val healthData = HealthData(
                    Nausea = nausea,
                    Headache = headache,
                    Diarrhea = diarrhea,
                    SoarThroat = soreThroat,
                    Fever = fever,
                    MuscleAche = muscleAche,
                    LossofSmellorTaste = lossOfSmellOrTaste,
                    Cough = cough,
                    ShortnessofBreath = shortnessOfBreath,
                    FeelingTired = feelingTired,
                    HeartRate = heartRate,
                    RespiratoryRate = respiratoryRate
                )
                viewModel.insert(healthData)
                Log.d("Database", "Data inserted successfully")
            } catch (e: Exception) {
                Log.e("Database", "Error inserting data: ${e.message}")
            }
        }
    }
}
