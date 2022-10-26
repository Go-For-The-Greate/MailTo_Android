package com.woga.data.di

import com.woga.data.datasource.MailHistoryLocalDataSource
import com.woga.data.repository.MailRepository
import com.woga.data.repository.MailRepositoryImpl
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

object RepositoryInjector {
    fun provideMailRepository(localDataSource: MailHistoryLocalDataSource, ioDispatcher: CoroutineDispatcher = Dispatchers.IO): MailRepository {
        return MailRepositoryImpl(
            localDataSource
        )
    }
}