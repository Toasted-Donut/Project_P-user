package com.example.client.data


import android.content.Context
import android.util.Log
import com.example.client.data.models.Check
import com.example.client.data.models.CheckItem
import com.example.client.ui.MainActivity
import kotlinx.coroutines.*
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import java.io.*
import java.text.SimpleDateFormat
import java.util.*
import javax.mail.Folder
import javax.mail.Message
import javax.mail.Session
import javax.mail.internet.MimeMultipart
import kotlin.Any
import kotlin.Exception
import kotlin.String
import kotlin.collections.ArrayList
import kotlin.coroutines.CoroutineContext

class Mail(private val mailProps: Properties, private val mainActivity: MainActivity): CoroutineScope {
    private val user: String = mailProps.getProperty("mail.imap.user")
    private val password: String = mailProps.getProperty("mail.imap.password")
    private val host: String = mailProps.getProperty("mail.imap.host")
    private val port: String = mailProps.getProperty("mail.imap.port")

    private var job = Job()

    suspend fun getMessages(){
        Log.i("prop", "$host $port $user $password")
        try{
            val store = Session.getInstance(mailProps).store; store.connect(host, user, password)
            val inbox = store.getFolder("INBOX"); inbox.open(Folder.READ_ONLY)
            val checks = ArrayList<Check>()
            val checkItems = ArrayList<CheckItem>()
            Log.i("gg",inbox.messageCount.toString())
            for (message in inbox.messages){
                if (message.subject.contains(other = "чек",ignoreCase = true)){
                    Log.i("gg",message.subject)
                    val messageInfo = getTextFromMessage(message)
                    if (messageInfo!=null){
                        val checkPath = String.format("${message.messageNumber} ${message.subject}.html")
                        withContext(Dispatchers.IO) {
                            if (!File(checkPath).exists()){
                                val jowWrite = launch {
                                    writeCheckToMemory(checkPath, messageInfo)
                                }
                                jowWrite.join()
                            }
                            val fileInputStream = mainActivity.openFileInput(checkPath)
                            val doc: Document = Jsoup.parse(readFromInputStream(fileInputStream))
                            val date = doc.select(":containsOwn(Дата | Время) + td").text()
                            val checkId = SimpleDateFormat("dd.MM.yyyy | HH:mm").parse(date)!!.time
                            checks.add(Check(
                                id = checkId,
                                date = date,
                                filepath = String.format("${mainActivity.filesDir}/$checkPath")
                            ))
                            try {
                                var counter = 0
                                while (true){
                                    val itemName = doc.select("span[style=\"line-height: 21px; color: #000000; font-weight: bold;\"]")[counter*2 + 1].text()
                                    val itemSum = (doc.select(":containsOwn(Сумма) + td")[counter].text()).toFloat()
                                    checkItems.add(CheckItem(
                                        sum = itemSum,
                                        checkId = checkId,
                                        productName = itemName
                                    ))
                                    counter++
                                }
                            }catch (ignored: Exception){}
                            fileInputStream.close()

                        }
                    }
                }
            }
            Log.wtf("gg","start saving")
            mainActivity.commonViewModel.insertCheck(checks,checkItems)
            Log.wtf("gg","end saving")
            inbox.close(true)
        }catch (ex: Exception){
            Log.e("mail_error",ex.message.toString())
        }

    }
    private suspend fun writeCheckToMemory(checkPath: String, messageInfo: Any){
        withContext(Dispatchers.IO) {
            val objectOutputStream = ObjectOutputStream(BufferedOutputStream(mainActivity.openFileOutput(checkPath, Context.MODE_PRIVATE)))
            objectOutputStream.writeObject(messageInfo)
            objectOutputStream.close()
        }
    }
    private fun readFromInputStream(inputStream: InputStream): String{
        val stringBuilder = StringBuilder()
        val bufferedReader = BufferedReader(InputStreamReader(inputStream))
        var line = bufferedReader.readLine()
        while (line != null) {
            stringBuilder.append(line).append('\n');
            line = bufferedReader.readLine()
        }
        return stringBuilder.toString()
    }
    private fun getTextFromMessage(message: Message): Any?{
        if (message.isMimeType("multipart/*")){
            return ((message.content as MimeMultipart).getBodyPart(0).content  as MimeMultipart).getBodyPart(0).content
        }
        return null
    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO + job
}