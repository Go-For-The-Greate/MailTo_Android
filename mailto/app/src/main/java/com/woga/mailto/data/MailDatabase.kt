package com.woga.mailto.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [MailEntity::class], version = 1)
abstract class MailDatabase : RoomDatabase() {
    abstract fun mailDao(): MailDAO

    companion object {
        private var INSTANCE: MailDatabase? = null

        fun getInstance(context: Context): MailDatabase {
            synchronized(MailDatabase) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        MailDatabase::class.java, "mail_database"
                    ) // Wipes and rebuilds instead of migrating
                        // if no Migration object.
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return INSTANCE ?: throw IllegalStateException()
        }
    }
}