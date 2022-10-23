package com.woga.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.woga.data.model.MailEntity


@Database(entities = [MailEntity::class], version = 1)
abstract class MailDatabase : RoomDatabase() {
    abstract fun mailDao(): MailDAO
}