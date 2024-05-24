package com.example.client.ui.checks

import android.app.Application
import androidx.lifecycle.*
import com.example.client.data.db.MailDatabase
import com.example.client.data.models.CategorySum
import com.example.client.data.models.Check
import com.example.client.data.models.CheckItem
import com.example.client.data.repo.MailRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CommonViewModel(
    val repository: MailRepository
) : ViewModel(){

    val allChecks: LiveData<List<Check>> = repository.getAllChecks()
    val categorySumLvData = MutableLiveData<List<CategorySum>>()
    fun insertCheck(checks: List<Check>, checkItems: List<CheckItem>){
        viewModelScope.launch(Dispatchers.IO){
            repository.save(*checks.map { it }.toTypedArray())
            repository.save(*checkItems.map { it }.toTypedArray())
        }
    }
    fun setChartData(start: Long, end: Long){
        viewModelScope.launch{
            categorySumLvData.value = repository.getCheckItemsInDateRange(start, end)
        }
    }

}