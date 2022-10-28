package com.woga.mailto

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.woga.data.di.DAOInjector
import com.woga.data.di.DataSourceInjector
import com.woga.data.di.MailDataBaseInjector
import com.woga.data.di.RepositoryInjector

class MailListViewModelFactory(val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MailListViewModel::class.java)) {
            return MailListViewModel(
                RepositoryInjector.provideMailRepository(
                    DataSourceInjector.provideMailHistoryDataSource(
                        DAOInjector.provideDAO(
                            MailDataBaseInjector.provideMailDatabase(context)
                        )
                    )
                )
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}