package com.example.client.ui

import android.app.Application
import androidx.room.Room
import com.example.client.data.db.MailDatabase
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MyApp : Application() {
    companion object {
        lateinit var mailDB: MailDatabase
        lateinit var retrofit: Retrofit
    }

    override fun onCreate() {
        super.onCreate()
        mailDB = MailDatabase.getDatabase(applicationContext)
        retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.3.4/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}