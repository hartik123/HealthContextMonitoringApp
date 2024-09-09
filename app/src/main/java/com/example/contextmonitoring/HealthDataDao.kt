package com.example.contextmonitoring

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface HealthDataDao {

    @Insert
    fun insert(healthData: HealthData)

//    @Query("SELECT * FROM health_data")
//    suspend fun getAllHealthData(): List<HealthData>
}
