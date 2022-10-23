package com.woga.data.repository

import com.woga.data.model.MailEntity

interface MailRepository {
    suspend fun getAll(): Result<List<MailEntity>>
    suspend fun insert(entity: MailEntity): Result<Unit>
}