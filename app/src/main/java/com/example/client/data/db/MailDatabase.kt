package com.example.client.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.client.data.daos.CommonDao
import com.example.client.data.models.Category
import com.example.client.data.models.Check
import com.example.client.data.models.CheckItem
import com.example.client.data.models.Product


@Database(entities = [Check::class, CheckItem::class, Product::class, Category::class], version = 1, exportSchema = false)
abstract class MailDatabase : RoomDatabase() {

    abstract fun getDao(): CommonDao

    companion object{
        @Volatile
        private var INSTANCE: MailDatabase? = null

        private const val DB_NAME = "mail_database"

        fun getDatabase(context: Context): MailDatabase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MailDatabase::class.java,
                    DB_NAME
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}