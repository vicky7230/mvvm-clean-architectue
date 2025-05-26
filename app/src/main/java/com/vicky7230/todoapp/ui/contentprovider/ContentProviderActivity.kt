package com.vicky7230.todoapp.ui.contentprovider

import android.os.Bundle
import android.provider.ContactsContract
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.vicky7230.todoapp.R

class ContentProviderActivity : AppCompatActivity() {
    lateinit var textView: AppCompatTextView
    private val projections = arrayOf(
        ContactsContract.Contacts.DISPLAY_NAME,
        ContactsContract.Contacts.CONTACT_STATUS,
        ContactsContract.Contacts.HAS_PHONE_NUMBER
    )
    private val selectionClause = ContactsContract.Contacts.DISPLAY_NAME_PRIMARY + " = ?"
    private val selectionArguments = arrayOf("Ajay Singh")
    private val orderBy = ContactsContract.Contacts.DISPLAY_NAME_PRIMARY

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_content_provider)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        textView = findViewById(R.id.text_view)

        val cursor = contentResolver.query(
            ContactsContract.Contacts.CONTENT_URI,
            projections,
            null,
            null,
            orderBy
        )

        if (cursor != null && cursor.count > 0) {
            val stringBuilder = StringBuilder()
            while (cursor.moveToNext()){
                stringBuilder.append(cursor.getString(0)+" , "+ cursor.getString(1)+" , "+ cursor.getString(2)+ "\n")
            }
            textView.setText(stringBuilder.toString())
        } else {
            textView.setText("No Contacts in device")
        }
    }
}