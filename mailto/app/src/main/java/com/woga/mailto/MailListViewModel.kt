package com.woga.mailto

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.woga.data.model.MailVO
import com.woga.data.repository.MailRepository
import kotlinx.coroutines.launch

class MailListViewModel(private val repository: MailRepository) : ViewModel() {

    private val _items: MutableLiveData<List<MailVO>> = MutableLiveData()
    val items: LiveData<List<MailVO>>
        get() = _items

    fun init() {
        viewModelScope.launch {
            val result = repository.getAll()
            if (result.isSuccess) {
                _items.postValue(result.getOrDefault(listOf()))
            }
        }
    }
}