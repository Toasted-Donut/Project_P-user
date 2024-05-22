package com.example.client.ui.checks

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.client.data.db.MailDatabase
import com.example.client.data.models.Check
import com.example.client.data.repo.MailRepository
import kotlinx.coroutines.Dispatchers
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
    fun insertCheck(vararg check: Check){
        viewModelScope.launch(Dispatchers.IO) {repository.save(*check)}
    }
}