package com.woga.data.repository

import com.woga.data.datasource.MailHistoryLocalDataSource
import com.woga.data.model.MailEntity
import com.woga.data.model.MailVO
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MailRepositoryImpl(
    private val localDataSource: MailHistoryLocalDataSource,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : MailRepository {
    override suspend fun getAll(): Result<List<MailVO>> = runCatching {
       withContext(ioDispatcher) {
           localDataSource.getAll()
       }
    }

    override suspend fun insert(vo: MailVO): Result<Unit> = runCatching {
        withContext(ioDispatcher) {
            localDataSource.insert(vo)
        }
    }
}