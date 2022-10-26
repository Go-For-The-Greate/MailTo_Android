package com.woga.data.di

import com.woga.data.MailDAO
import com.woga.data.MailDatabase

object DAOInjector {
    fun provideDAO(database: MailDatabase): MailDAO = database.mailDao()
}