package com.woga.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "mailbox")
data class MailEntity(
    @PrimaryKey(autoGenerate = true) val index: Int = 0,
    @ColumnInfo(name = "user_email") val userEmail: String?,
    @ColumnInfo(name = "title") val title: String?,
    @ColumnInfo(name = "content") val content: String?,
) {
    fun mapper(): MailVO {
        return MailVO(userEmail ?: "", title ?: "", content ?: "")
    }
}