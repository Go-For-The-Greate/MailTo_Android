package com.woga.data.di

import com.woga.data.MailDAO
import com.woga.data.datasource.MailHistoryLocalDataSource
import com.woga.data.datasource.MailHistoryLocalDataSourceImpl

object DataSourceInjector {
    fun provideMailHistoryDataSource(mailDAO: MailDAO): MailHistoryLocalDataSource {
        return MailHistoryLocalDataSourceImpl(mailDAO)
    }
}