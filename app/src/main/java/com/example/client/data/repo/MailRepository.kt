package com.example.client.data.repo

import androidx.lifecycle.LiveData
import com.example.client.data.daos.CommonDao
import com.example.client.data.models.*


class MailRepository(private val dao: CommonDao) {

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
            if (dao.getProductByName(checkItem.productName!!).isEmpty()){
                save(Product(categoryId = 0, name = checkItem.productName))
            }
        }
        dao.save(*checkItems)
    }
    suspend fun save(vararg check: Check){
        dao.save(*check)
    }
}