package com.woga.mailto.write

sealed class MailWriteEvent {
    object Success : MailWriteEvent()
}
