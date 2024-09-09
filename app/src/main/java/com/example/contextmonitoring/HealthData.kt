package com.example.contextmonitoring

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "health_data")
data class HealthData(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val Nausea: Float,
    val Headache: Float,
    val Diarrhea: Float,
    val SoarThroat: Float,
    val Fever: Float,
    val MuscleAche: Float,
    val LossofSmellorTaste: Float,
    val Cough: Float,
    val ShortnessofBreath: Float,
    val FeelingTired: Float,
    val HeartRate: Float,
    val RespiratoryRate: Float
)
