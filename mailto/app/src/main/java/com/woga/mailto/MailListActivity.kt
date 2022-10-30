package com.woga.mailto

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.woga.data.model.MailVO

class MailListActivity : AppCompatActivity() {

    val viewModel: MailListViewModel by viewModels { MailListViewModelFactory(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent() {
            MailListView()
        }
    }

    @Composable
    fun MailListView() {
        val list = viewModel.items.observeAsState()
        LaunchedEffect(key1 = Unit) {
            viewModel.init()
        }
        LazyColumn() {
            itemsIndexed(items = list.value ?: listOf()) { index, item ->
                ItemView(item)
            }
        }
    }
    
    @Composable
    fun ItemView(item: MailVO) {
        Card(
            Modifier
                .padding(top = 16.dp, start = 20.dp, end = 16.dp)
                .fillMaxWidth()
                .height(100.dp)) 
        {
            Column() {
                Text(text = item.title, fontSize = 15.sp, maxLines = 1, overflow = TextOverflow.Ellipsis)
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = item.content, fontSize = 12.sp, maxLines = 2, overflow = TextOverflow.Ellipsis)
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = "작성자: ${item.email}", fontSize = 12.sp, maxLines = 1, overflow = TextOverflow.Ellipsis)
            }
        }
    }


}