package com.woga.mailto.write

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.woga.data.di.DAOInjector
import com.woga.data.di.DataSourceInjector
import com.woga.data.di.MailDataBaseInjector
import com.woga.data.di.RepositoryInjector
import com.woga.mailto.util.MailSenderManager

class MailWriteViewModelFactory(val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MailWriteViewModel::class.java)) {
            return MailWriteViewModel(
                RepositoryInjector.provideMailRepository(
                    DataSourceInjector.provideMailHistoryDataSource(
                        DAOInjector.provideDAO(
                            MailDataBaseInjector.provideMailDatabase(context)
                        )
                    )
                ),
                MailSenderManager()
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}