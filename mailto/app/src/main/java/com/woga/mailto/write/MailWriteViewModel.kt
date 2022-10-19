package com.woga.mailto.write

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.woga.mailto.data.MailEntity
import com.woga.mailto.data.MailDatabase
import com.woga.mailto.util.Event
import kotlinx.coroutines.launch
import java.util.regex.Matcher
import java.util.regex.Pattern

class MailWriteViewModel : ViewModel() {

    private val _isEmailPattern = MutableLiveData<Boolean>()
    val isEmailPattern: LiveData<Boolean>
        get() = _isEmailPattern

    private val _event = MutableLiveData<Event<MailWriteEvent>>()
    val event: LiveData<Event<MailWriteEvent>>
        get() = _event


    fun checkValidEmail(email: String) {
        val pattern: Pattern = Pattern.compile("^\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,3}\$")
        val matcher: Matcher = pattern.matcher(email)
        _isEmailPattern.value = matcher.matches()
    }

    fun sendEmail(email: String, title: String, content: String, instance: MailDatabase) {
        viewModelScope.launch {
            instance.mailDao().insert(MailEntity(userEmail = email, title = title, content = content))
            _event.value = Event(MailWriteEvent.Success)
        }

    }
}