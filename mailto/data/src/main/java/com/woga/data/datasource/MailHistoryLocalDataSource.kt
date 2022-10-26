package com.woga.data.datasource

import com.woga.data.model.MailVO

interface MailHistoryLocalDataSource {
    suspend fun getAll(): List<MailVO>
    suspend fun insert(vo: MailVO)
}