package com.woga.mailto.write

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.graphics.toColor
import com.woga.mailto.R

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
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.padding(top = 10.dp, start = 20.dp, end = 20.dp))
            var emailText by remember { mutableStateOf("") }

            OutlinedTextField(
                value = emailText,
                modifier = Modifier.fillMaxWidth(),
                onValueChange = { emailText = it },
                maxLines = 1,
                label = { Text("본인의 이메일을 적어주세요") }
            )

            Spacer(modifier = Modifier.padding(top = 10.dp, start = 20.dp, end = 20.dp))
            var title by remember { mutableStateOf("") }

            OutlinedTextField(
                value = title,
                modifier = Modifier.fillMaxWidth(),
                onValueChange = { title = it },
                maxLines = 1,
                label = { Text("제목") }
            )

            Spacer(modifier = Modifier.padding(top = 10.dp, start = 20.dp, end = 20.dp))
            var description by remember { mutableStateOf("") }

            OutlinedTextField(
                value = description,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp),
                onValueChange = { description = it },
                label = { Text("내용") }
            )
            Spacer(modifier = Modifier.padding(bottom = 10.dp))

            Spacer(modifier = Modifier.padding(top = 10.dp, start = 20.dp, end = 20.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Button(
                    modifier = Modifier.width(100.dp),
                    onClick = { ActivityCompat.finishAfterTransition(this@MailWriteActivity)},
                    colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(R.color.gray_60), contentColor = colorResource(R.color.gray_400))
                ) {
                    Text("취소")
                }
                Spacer(modifier = Modifier.padding(start = 20.dp, end = 20.dp))
                Button(
                    modifier = Modifier.width(100.dp),
                    onClick = { /*TODO*/ }
                ) {
                    Text("전송")
                }
            }
        }
    }

}