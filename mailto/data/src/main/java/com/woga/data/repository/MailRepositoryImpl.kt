package com.woga.data.repository

import com.woga.data.datasource.MailHistoryLocalDataSource
import com.woga.data.model.MailEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MailRepositoryImpl(
    private val localDatabase: MailHistoryLocalDataSource,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : MailRepository {
    override suspend fun getAll(): Result<List<MailEntity>> = runCatching {
       withContext(ioDispatcher) {
           localDatabase.getAll()
       }
    }

    override suspend fun insert(entity: MailEntity): Result<Unit> = runCatching {
        withContext(ioDispatcher) {
            localDatabase.insert()
        }
    }
}