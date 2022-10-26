package com.woga.data.repository

import com.woga.data.model.MailVO

interface MailRepository {
    suspend fun getAll(): Result<List<MailVO>>
    suspend fun insert(vo: MailVO): Result<Unit>
}