package com.woga.data.datasource

import com.woga.data.MailDAO
import com.woga.data.model.MailEntity
import com.woga.data.model.MailVO

class MailHistoryLocalDataSourceImpl(
    private val dao: MailDAO
) : MailHistoryLocalDataSource {
    override suspend fun getAll(): List<MailVO> {
        return dao.getAll().map {
            it.mapper()
        }
    }

    override suspend fun insert(vo: MailVO) {
        dao.insert(MailEntity(userEmail=vo.email, title = vo.title, content = vo.content))
    }
}