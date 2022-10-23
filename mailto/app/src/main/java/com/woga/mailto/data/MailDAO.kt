package com.woga.mailto.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MailDAO {
    @Query("SELECT * FROM mailbox")
    fun getAll(): List<MailEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(mailEntity : MailEntity)

    @Delete
    fun delete(email: MailEntity)
}