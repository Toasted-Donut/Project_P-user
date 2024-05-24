package com.example.client.ui

import android.app.Application
import androidx.room.Room
import com.example.client.data.db.MailDatabase

class MyApp : Application() {
    companion object {
        lateinit var mailDB: MailDatabase
    }

    override fun onCreate() {
        super.onCreate()
        mailDB = MailDatabase.getDatabase(applicationContext)
    }
}