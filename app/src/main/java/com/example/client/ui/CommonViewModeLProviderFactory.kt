package com.example.client.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.client.data.interfaces.ProductCategoryApi
import com.example.client.data.repo.MailRepository
import retrofit2.create

class CommonViewModeLProviderFactory(val app: MyApp) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val dao = MyApp.mailDB.getDao()
        val api = MyApp.retrofit.create(ProductCategoryApi::class.java)
        val mainRepository = MailRepository(
            dao = dao,
            api = api
        )
        val viewModel = CommonViewModel(mainRepository)
        return viewModel as T
    }
}