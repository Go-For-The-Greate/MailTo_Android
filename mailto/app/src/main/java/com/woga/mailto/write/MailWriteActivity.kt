package com.woga.mailto.write

import android.app.Activity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldColors
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.input.key.Key.Companion.I
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.core.app.ActivityCompat
import com.woga.mailto.R

class MailWriteActivity : AppCompatActivity() {


    private val viewModel: MailWriteViewModel by viewModels() { MailWriteViewModelFactory(this)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MailWriteView()
        }
        observe()
    }

    @Composable
    fun MailWriteView() {
        var showDialog by remember { mutableStateOf(false) }
        ShowProgressBar(showDialog)
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
                onValueChange = {
                    viewModel.checkValidEmail(it)
                    emailText = it
                },
                singleLine = true,
                maxLines = 1,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                label = {
                    EmailTextViewLabel(viewModel.isEmailPattern.value ?: true)
                },
                colors = getTextFieldColors(viewModel.isEmailPattern.value ?: true)
            )

            Spacer(modifier = Modifier.padding(top = 10.dp, start = 20.dp, end = 20.dp))

            var title by remember { mutableStateOf("") }

            OutlinedTextField(
                value = title,
                modifier = Modifier.fillMaxWidth(),
                onValueChange = { title = it },
                maxLines = 1,
                singleLine = true,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                label = { Text("제목") },
                colors = getTextFieldColors(true)
            )
            Spacer(modifier = Modifier.padding(top = 10.dp, start = 20.dp, end = 20.dp))
            var content by remember { mutableStateOf("") }

            OutlinedTextField(
                value = content,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp),
                onValueChange = { content = it },
                label = { Text("내용") },
                colors = getTextFieldColors(true)
            )
            Spacer(modifier = Modifier.padding(bottom = 10.dp))

            Spacer(modifier = Modifier.padding(top = 10.dp, start = 20.dp, end = 20.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Button(
                    shape = RoundedCornerShape(34.dp),
                    modifier = Modifier.width(100.dp),
                    onClick = { close() },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = colorResource(R.color.gray_60),
                        contentColor = colorResource(R.color.gray_400)
                    )
                ) {
                    Text("취소")
                }
                Spacer(modifier = Modifier.padding(start = 20.dp, end = 20.dp))
                Button(
                    shape = RoundedCornerShape(34.dp),
                    modifier = Modifier.width(100.dp),
                    onClick = {
                        showDialog = true
                        Handler(Looper.getMainLooper()).postDelayed({
                            viewModel.sendEmail(emailText, title, content)
                        }, 500)
                    }
                ) {
                    Text("전송")
                }
            }
        }
    }

    @Composable
    fun EmailTextViewLabel(isValidEmail: Boolean) {
        return if (isValidEmail) {
            Text("본인의 이메일을 적어주세요")
        } else {
            Text("이메일 양식이 올바르지 않습니다.")
        }
    }

    @Composable
    fun getTextFieldColors(isValidEmail: Boolean): TextFieldColors {
        return TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = if (isValidEmail) colorResource(R.color.green_600) else colorResource(R.color.red_900),
            unfocusedBorderColor = colorResource(R.color.gray_600),
            focusedLabelColor = if (isValidEmail) colorResource(R.color.green_600) else colorResource(R.color.red_900),
            cursorColor = colorResource(R.color.gray_600)
        )
    }

    @Composable
    fun ShowProgressBar(dialogState: Boolean) {
        var showDialog = dialogState
        if (showDialog) {
            Dialog(
                onDismissRequest = { showDialog = false },
                DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false)
            ) {
                Box(
                    contentAlignment= Alignment.Center,
                    modifier = Modifier
                        .size(100.dp)
                        .background(Transparent, shape = RoundedCornerShape(8.dp))
                ) {
                    CircularProgressIndicator()
                }
            }
        }
    }

    private fun observe() {
        viewModel.event.observe(this) {
            it.getContentIfNotHandled()?.let { mailEvent ->
                when(mailEvent) {
                    is MailWriteEvent.Success -> showSuccessSendingMail()
                }
            }
        }
    }

    private fun showSuccessSendingMail() {
        Toast.makeText(this, "메일을 선공적으로 전송하였습니다!", Toast.LENGTH_SHORT).show()
        setResult(Activity.RESULT_OK)
        close()
    }

    private fun close() {
        ActivityCompat.finishAfterTransition(this@MailWriteActivity)
    }

}