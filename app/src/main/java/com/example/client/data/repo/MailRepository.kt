package com.example.client.data.repo

import androidx.lifecycle.LiveData
import com.example.client.data.daos.CommonDao
import com.example.client.data.models.*


class MailRepository(private val dao: CommonDao) {

    fun getAllChecks(): LiveData<List<Check>> = dao.getAllChecks()

    //suspend fun getCheckItemsInDateRange(start: Long, end: Long): List<CategorySum> = dao.getCheckItemsInDateRange(start,end)


    suspend fun save(vararg category: Category){
        dao.save(*category)
    }
    suspend fun save(vararg product: Product){
        dao.save(*product)
    }
    suspend fun save(vararg checkItem: CheckItem){
        dao.save(*checkItem)
    }
    suspend fun save(vararg check: Check){
        dao.save(*check)
    }
}