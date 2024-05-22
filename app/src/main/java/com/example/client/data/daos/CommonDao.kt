package com.example.client.data.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.client.data.models.*

@Dao
interface CommonDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(vararg category: Category)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(vararg  product: Product)

    @Insert
    suspend fun save(vararg checkItem: CheckItem)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun save(vararg check: Check)



    @Delete
    suspend fun delete(vararg category: Category)

    @Delete
    suspend fun delete(vararg  product: Product)

    @Delete
    suspend fun delete(vararg checkItem: CheckItem)

    @Delete
    suspend fun delete(vararg check: Check)



    @Query("SELECT * FROM 'check'")
    fun getAllChecks(): LiveData<List<Check>>


//    @Transaction
//    @Query("""
//        SELECT product.category_ref_id AS category_ref_id, SUM(check_item.sum) AS sum
//        FROM check_item
//        JOIN product ON check_item.product_ref_name = product.name
//        WHERE check_item.check_ref_id BETWEEN :start AND :end
//        GROUP BY product.category_ref_id
//    """)
//    suspend fun getCheckItemsInDateRange(start: Long, end: Long): List<CategorySum>

}