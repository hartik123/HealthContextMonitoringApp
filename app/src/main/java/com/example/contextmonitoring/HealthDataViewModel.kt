package com.example.contextmonitoring

import com.example.contextmonitoring.AppDatabase
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HealthDataViewModel(application: Application) : AndroidViewModel(application) {
    private val healthDataDao: HealthDataDao
    private val database: AppDatabase = AppDatabase.getInstance(application)

    init {
        healthDataDao = database.healthDataDao()
    }

    fun insert(healthData: HealthData) {
        viewModelScope.launch((Dispatchers.IO)) {
            healthDataDao.insert(healthData)
        }
    }
}
