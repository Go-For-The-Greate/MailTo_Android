package com.woga.mailto.write

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.regex.Matcher
import java.util.regex.Pattern

class MailWriteViewModel : ViewModel() {

    private val _isEmailPattern = MutableLiveData<Boolean>()
    val isEmailPattern: LiveData<Boolean>
        get() = _isEmailPattern


    fun checkValidEmail(email: String) {
        val pattern: Pattern = Pattern.compile("^\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,3}\$")
        val matcher: Matcher = pattern.matcher(email)
        _isEmailPattern.value = matcher.matches()
    }

    fun sendEmail(email: String, title: String, content: String) {

    }
}