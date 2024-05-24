package com.example.client.data.repo

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.client.data.interfaces.CommonDao
import com.example.client.data.interfaces.ProductCategoryApi
import com.example.client.data.models.*
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.Body


class MailRepository(
    private val dao: CommonDao,
    private val api: ProductCategoryApi) {

    fun getAllChecks(): LiveData<List<Check>> = dao.getAllChecks()

    //suspend fun grtAllCategories(): List<Category> = dao.getAllCategories()
    suspend fun getCheckItemsInDateRange(start: Long, end: Long): List<CategorySum> = dao.getCheckItemsInDateRange(start,end)

    suspend fun save(vararg category: Category){
        dao.save(*category)
    }
    suspend fun save(vararg product: Product){
        dao.save(*product)
    }
    suspend fun save(vararg checkItems: CheckItem){
        for (checkItem in checkItems){
            if (dao.getProductByName(checkItem.productName).isEmpty()){
                Log.w("gg",Gson().toJson(Product(checkItem.productName,0)))
                val call = api.postProduct(Product(checkItem.productName,0))
                Log.w("gg",call.name)
                save(call)

            }
        }
        dao.save(*checkItems)
    }
    suspend fun save(vararg check: Check){
        dao.save(*check)
    }
    suspend fun fetchCategories(){
        dao.save(*api.getCategories().map { it }.toTypedArray())
    }

    suspend fun fetchProducts(){
        dao.save(*api.getProducts().map { it }.toTypedArray())
    }
}