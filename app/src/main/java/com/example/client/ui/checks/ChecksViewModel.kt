package com.example.client.ui.checks

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.client.data.db.MailDatabase
import com.example.client.data.models.Check
import com.example.client.data.models.CheckItem
import com.example.client.data.repo.MailRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class ChecksViewModel(
    application: Application
) : AndroidViewModel(application){

    val allChecks: LiveData<List<Check>>
    val repository: MailRepository

    init {
        val dao = MailDatabase.getDatabase(application).getDao()
        repository = MailRepository(dao)
        allChecks = repository.getAllChecks()
    }
    fun insertCheck(checks: List<Check>, checkItems: List<CheckItem>){
        viewModelScope.launch(Dispatchers.IO){
            repository.save(*checks.map { it }.toTypedArray())
            repository.save(*checkItems.map { it }.toTypedArray())
        }
    }
}