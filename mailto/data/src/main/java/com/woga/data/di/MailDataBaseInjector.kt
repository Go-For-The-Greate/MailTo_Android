package com.woga.data.di

import android.content.Context
import androidx.room.Room
import com.woga.data.MailDatabase

object MailDataBaseInjector {
    fun provideMailDatabase(context: Context): MailDatabase = Room.databaseBuilder(
        context,
        MailDatabase::class.java, "mailbox"
    ).build()
}