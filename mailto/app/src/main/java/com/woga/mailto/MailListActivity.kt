package com.woga.mailto

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Card
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.woga.data.model.MailVO
import com.woga.mailto.write.MailWriteActivity

class MailListActivity : AppCompatActivity() {

    val viewModel: MailListViewModel by viewModels { MailListViewModelFactory(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        val splash = installSplashScreen()
        super.onCreate(savedInstanceState)
        setContent() {
            MailListView()
            FloatingMailWriteButton()
        }
    }

    @Composable
    fun MailListView() {
        val list = viewModel.items.observeAsState()
        LaunchedEffect(key1 = Unit) {
            viewModel.getMailList()
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

    @Composable
    fun FloatingMailWriteButton() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .fillMaxHeight()
                .fillMaxWidth()
                .padding(20.dp),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.End
        ) {
            FloatingActionButton(
                onClick = { moveToMailList() },
                backgroundColor = colorResource(R.color.purple_200),
                contentColor =  colorResource(R.color.white)
            ){
                Icon(Icons.Filled.Add,"")
            }
        }
    }

    private val startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
        if (result.resultCode == Activity.RESULT_OK) {
            viewModel.getMailList()
        }
    }

    private fun moveToMailList() {
        startForResult.launch(Intent(this, MailWriteActivity::class.java))
    }

}