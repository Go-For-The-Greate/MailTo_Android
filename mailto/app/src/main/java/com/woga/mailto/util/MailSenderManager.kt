package com.woga.mailto.util

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import javax.mail.Authenticator
import javax.mail.Message
import javax.mail.PasswordAuthentication
import javax.mail.Session
import javax.mail.Transport
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage

class MailSenderManager : Authenticator() {
    // 보내는 사람 이메일과 비밀번호
    private val fromEmail = "supermiyeok@gmail.com"
    private val password = "epjhjgbctjwetfzg"

    // 보내는 사람 계정 확인
    override fun getPasswordAuthentication(): PasswordAuthentication {
        return PasswordAuthentication(fromEmail, password)
    }

    fun sendMail(toEmail: String, title: String, content: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val props = Properties()
            props.setProperty("mail.transport.protocol", "smtp")
            props.setProperty("mail.host", "smtp.gmail.com")
            props.put("mail.smtp.auth", "true")
            props.put("mail.smtp.port", "465")
            props.put("mail.smtp.socketFactory.port", "465")
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory")
            props.put("mail.smtp.socketFactory.fallback", "false")
            props.setProperty("mail.smtp.quitwait", "false")

            val session = Session.getDefaultInstance(props, this@MailSenderManager)

            val message = MimeMessage(session)
            message.sender = InternetAddress(fromEmail)
            message.addRecipient(Message.RecipientType.TO, InternetAddress(toEmail))
            message.subject = title
            message.setText(content)

            Transport.send(message)
        }
    }
}