package com.woga.mailto.write

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.material.Text
import androidx.compose.ui.Alignment

class MailWriteActivity : AppCompatActivity() {


    val viewModel: MailWriteViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            mailWriteView()
        }
    }

    @Composable
    fun mailWriteView() {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            var emailText by remember { mutableStateOf("") }

            OutlinedTextField(
                value = emailText,
                onValueChange = { emailText = it },
                maxLines = 1,
                label = { Text("본인의 이메일을 적어주세요") }
            )

            var title by remember { mutableStateOf("") }

            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                maxLines = 1,
                label = { Text("제목") }
            )

            var description by remember { mutableStateOf("") }

            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                label = { Text("내용") }
            )
        }
    }

}