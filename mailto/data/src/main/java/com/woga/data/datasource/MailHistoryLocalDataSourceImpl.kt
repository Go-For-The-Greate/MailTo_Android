package com.woga.data.datasource

import com.woga.data.MailDAO
import com.woga.data.model.MailEntity

class MailHistoryLocalDataSourceImpl(
    private val dao: MailDAO
) : MailHistoryLocalDataSource {
    override suspend fun getAll(): List<MailEntity> {
        return dao.getAll()
    }

    override suspend fun insert(entity: MailEntity) {
        dao.insert(entity)
    }
}