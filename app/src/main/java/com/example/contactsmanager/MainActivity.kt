package com.example.contactsmanager


import android.R.attr
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_contact.*


class MainActivity : AppCompatActivity() , ContactsCallback {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        var contacts = arrayListOf<Contact>(
            Contact(BitmapFactory.decodeResource(resources,R.drawable.boris_johnson),
            "Mr", "Boris","Johnson","boris1964@gmail.com","+440 12 345 67 89"),
            Contact(BitmapFactory.decodeResource(resources,R.drawable.peter_parker),
                "Mr", "Peter","Parker","parker2001@gmail.com","+100 12 345 67 89"),
            Contact(BitmapFactory.decodeResource(resources,R.drawable.angela_merkel),
                "Mrs", "Angela","Merkel","angela1954@gmail.com","+490 12 345 67 89"),
            Contact(BitmapFactory.decodeResource(resources,R.drawable.volodimir_putin),
                "Mr", "Vladimir","Putin","vvwho1952@gmail.com","+735 12 345 67 89"),
            Contact(BitmapFactory.decodeResource(resources,R.drawable.volodimir_zelenskyi),
                "Mr", "Volodimir","Zelenskyi","zelya1978@gmail.com","+380 12 345 67 89")

        )
        rvContacts.adapter = ContactsAdapter(this,contacts, this)
        rvContacts.layoutManager = LinearLayoutManager (this)
    }

    override fun onItemSelected(index: Int) {
        Log.d("MyLog", "You are a good girl")
        Toast.makeText(this,R.string.change, Toast.LENGTH_LONG).show()
    }
          val SELECT_PICTURE =1
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        /*if (requestCode == EXTRA_LOCAL_ONLY && resultCode == RESULT_OK && null != data) {
            val selectedImage = data.data
            val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)
            val cursor: Cursor? = contentResolver.query(
                selectedImage!!,
                filePathColumn, null, null, null
            )
            cursor!!.moveToFirst()
            val columnIndex: Int = cursor.getColumnIndex(filePathColumn[0])
            val picturePath: String = cursor.getString(columnIndex)
            cursor.close()
            val imageView: ImageView = findViewById<View>(R.id.ivItemPhoto) as ImageView
            imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath))
        }*/

        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {
                val imageURI: Uri = data?.getData()!!


                Picasso.get().load(imageURI).into(ivItemPhoto, object : Callback {
                        override fun onSuccess() {
                            Log.d("MyLog", "success")
                        }

                        override fun onError(e: Exception?) {
                            Log.d("MyLog", "error")
                        }
                    })
            }

        }
    }

}