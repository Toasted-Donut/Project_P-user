package com.example.client.ui

import android.content.Intent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.client.data.repo.MailRepository
import com.example.client.ui.checks.CommonViewModel

class CommonViewModeLProviderFactory(val app: MyApp) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val dao = MyApp.mailDB.getDao()
        val mainRepository = MailRepository(
            dao=dao
        )
        val viewModel = CommonViewModel(mainRepository)
        return viewModel as T
    }
}