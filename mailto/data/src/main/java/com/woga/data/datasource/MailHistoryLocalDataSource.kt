package com.woga.data.datasource

import com.woga.data.model.MailEntity

interface MailHistoryLocalDataSource {
    suspend fun getAll(): List<MailEntity>
    suspend fun insert(entity: MailEntity)
}