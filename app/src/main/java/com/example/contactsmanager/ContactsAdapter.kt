package com.example.contactsmanager

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_contact.view.*
import android.widget.Toast.makeText
import androidx.core.graphics.set


class ContactsAdapter(val context: Context, var contacts : ArrayList<Contact>,
                      val callback: ContactsCallback) : RecyclerView.Adapter<ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_contact, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {


        fun setUp(contacts: ArrayList<Contact>) {
            holder.photo.setImageBitmap(contacts[position].photo)
            holder.sex.text = contacts[position].sex
            holder.name.text = contacts[position].name
            holder.lastName.text = contacts[position].lastName
            holder.email.text = contacts[position].email
            holder.tel.text = contacts[position].tel
        }
        setUp(contacts)
        holder.tvItemAdjust.setOnClickListener {
            callback.onItemSelected(position)
            holder.photo.setOnClickListener {
                holder.contact.visibility = View.INVISIBLE
                holder.clEdit.visibility = View.VISIBLE
                holder.etSex.visibility = View.INVISIBLE
                holder.etName.visibility = View.INVISIBLE
                holder.etLastName.visibility = View.INVISIBLE
                holder.etEmail.visibility = View.INVISIBLE
                holder.etPhone.visibility = View.INVISIBLE
                holder.btLoadPhoto.visibility = View.VISIBLE
                holder.btLoadPhoto.setOnClickListener {
                    val intent = Intent(Intent.ACTION_GET_CONTENT)
                    intent.type = "image/*"
                    val SELECT_PICTURE = 1
                    startActivityForResult(
                        Intent.createChooser(intent, "Select Picture"), SELECT_PICTURE
                    )
                    callback.onActivityResult(1, 1, data = intent)
                }
                holder.btSetUp.setOnClickListener {
                    setUp(contacts)
                    holder.contact.visibility = View.VISIBLE
                    holder.clEdit.visibility = View.INVISIBLE
                }
            }
            holder.sex.setOnClickListener {
                holder.contact.visibility = View.INVISIBLE
                holder.clEdit.visibility = View.VISIBLE
                holder.etSex.visibility = View.VISIBLE
                holder.btLoadPhoto.visibility = View.INVISIBLE
                holder.etName.visibility = View.INVISIBLE
                holder.etLastName.visibility = View.INVISIBLE
                holder.etEmail.visibility = View.INVISIBLE
                holder.etPhone.visibility = View.INVISIBLE
                holder.btSetUp.visibility = View.VISIBLE
                holder.etSex.afterTextChanged {
                    contacts[position].sex = holder.etSex.text.toString()
                    holder.btSetUp.setOnClickListener {
                        setUp(contacts)
                        holder.contact.visibility = View.VISIBLE
                        holder.clEdit.visibility = View.INVISIBLE
                    }
                }
            }
            holder.name.setOnClickListener {
                holder.contact.visibility = View.INVISIBLE
                holder.clEdit.visibility = View.VISIBLE
                holder.etSex.visibility = View.INVISIBLE
                holder.btLoadPhoto.visibility = View.INVISIBLE
                holder.etName.visibility = View.VISIBLE
                holder.etLastName.visibility = View.INVISIBLE
                holder.etEmail.visibility = View.INVISIBLE
                holder.etPhone.visibility = View.INVISIBLE
                holder.btSetUp.visibility = View.VISIBLE
                holder.etName.afterTextChanged {
                    contacts[position].name = holder.etName.text.toString()
                    holder.btSetUp.setOnClickListener {
                        setUp(contacts)
                        holder.contact.visibility = View.VISIBLE
                        holder.clEdit.visibility = View.INVISIBLE
                    }
                }
            }
            holder.lastName.setOnClickListener {
                holder.contact.visibility = View.INVISIBLE
                holder.clEdit.visibility = View.VISIBLE
                holder.etSex.visibility = View.INVISIBLE
                holder.btLoadPhoto.visibility = View.INVISIBLE
                holder.etName.visibility = View.INVISIBLE
                holder.etLastName.visibility = View.VISIBLE
                holder.etEmail.visibility = View.INVISIBLE
                holder.etPhone.visibility = View.INVISIBLE
                holder.btSetUp.visibility = View.VISIBLE
                holder.etLastName.afterTextChanged {
                    contacts[position].lastName = holder.etLastName.text.toString()
                    holder.btSetUp.setOnClickListener {
                        setUp(contacts)
                        holder.contact.visibility = View.VISIBLE
                        holder.clEdit.visibility = View.INVISIBLE
                    }
                }
            }
            holder.email.setOnClickListener {
                holder.contact.visibility = View.INVISIBLE
                holder.clEdit.visibility = View.VISIBLE
                holder.etSex.visibility = View.INVISIBLE
                holder.btLoadPhoto.visibility = View.INVISIBLE
                holder.etName.visibility = View.INVISIBLE
                holder.etLastName.visibility = View.INVISIBLE
                holder.etEmail.visibility = View.VISIBLE
                holder.etPhone.visibility = View.INVISIBLE
                holder.btSetUp.visibility = View.VISIBLE
                holder.etEmail.afterTextChanged {
                    contacts[position].email = holder.etEmail.text.toString()
                    holder.btSetUp.setOnClickListener {
                        setUp(contacts)
                        holder.contact.visibility = View.VISIBLE
                        holder.clEdit.visibility = View.INVISIBLE
                    }
                }
            }

        }
        holder.fab.setOnClickListener {

            holder.contact.visibility = View.INVISIBLE
            holder.clEdit.visibility = View.VISIBLE

          holder.btLoadPhoto.setOnClickListener {

                val intent = Intent(Intent.ACTION_GET_CONTENT)
                intent.type = "image/*"
                val SELECT_PICTURE = 1
                startActivityForResult(
                    Intent.createChooser(intent, "Select Picture"), SELECT_PICTURE
                )
                callback.onActivityResult(1, 1, data = intent)
            }

            holder.etSex.afterTextChanged {
                contacts[contacts.size+1].sex = holder.etSex.text.toString()
            }
            holder.etName.afterTextChanged {
                contacts[contacts.size+1].name = holder.etName.text.toString()
            }
            holder.etLastName.afterTextChanged {
                contacts[contacts.size+1].lastName = holder.etLastName.text.toString()
            }
            holder.etEmail.afterTextChanged {
                contacts[contacts.size+1].email = holder.etEmail.text.toString()
            }
            holder.etPhone.afterTextChanged {
                contacts[contacts.size+1].tel = holder.etPhone.text.toString()
            }


            holder.btSetUp.setOnClickListener {
                setUp(contacts)

                holder.contact.visibility = View.VISIBLE
                holder.clEdit.visibility = View.INVISIBLE
            }
        }
        holder.contact.setOnClickListener {
            holder.delete.visibility = View.VISIBLE
            holder.tel.visibility = View.VISIBLE

                holder.fab.setOnClickListener {

                holder.contact.visibility = View.INVISIBLE
                holder.clEdit.visibility = View.VISIBLE

                holder.btLoadPhoto.setOnClickListener {

                    val intent = Intent(Intent.ACTION_GET_CONTENT)
                    intent.type = "image/*"
                    val SELECT_PICTURE = 1
                    startActivityForResult(
                        Intent.createChooser(intent, "Select Picture"), SELECT_PICTURE
                    )
                    callback.onActivityResult(1, 1, data = intent)
                }

                holder.etSex.afterTextChanged {
                    contacts[contacts.size+1].sex = holder.etSex.text.toString()
                }
                holder.etName.afterTextChanged {
                    contacts[contacts.size+1].name = holder.etName.text.toString()
                }
                holder.etLastName.afterTextChanged {
                    contacts[contacts.size+1].lastName = holder.etLastName.text.toString()
                }
                holder.etEmail.afterTextChanged {
                    contacts[contacts.size+1].email = holder.etEmail.text.toString()
                }
                holder.etPhone.afterTextChanged {
                    contacts[contacts.size+1].tel = holder.etPhone.text.toString()
                }


                holder.btSetUp.setOnClickListener {
                    setUp(contacts)

                    holder.contact.visibility = View.VISIBLE
                    holder.clEdit.visibility = View.INVISIBLE
                }
            }
                holder.btItemDelete.setOnClickListener {
                    holder.contact.visibility = View.INVISIBLE
                    holder.tvDialog.visibility = View.VISIBLE
                    holder.tvDialogNo.visibility = View.VISIBLE
                    holder.tvDialogYes.visibility = View.VISIBLE
                    holder.tvDialogNo.setOnClickListener {
                        holder.tvDialog.visibility = View.INVISIBLE
                        holder.tvDialogNo.visibility = View.INVISIBLE
                        holder.tvDialogYes.visibility = View.INVISIBLE
                        holder.contact.visibility = View.VISIBLE
                    }
                    holder.tvDialogYes.setOnClickListener {

                        contacts.remove(contacts[position])

                        setUp(contacts)

                        holder.tvDialog.visibility = View.INVISIBLE
                        holder.tvDialogNo.visibility = View.INVISIBLE
                        holder.tvDialogYes.visibility = View.INVISIBLE
                        holder.contact.visibility = View.VISIBLE
                    }
                }
                holder.tvItemAdjust.setOnClickListener {
                    callback.onItemSelected(position)
                    holder.photo.setOnClickListener {
                        holder.contact.visibility = View.INVISIBLE
                        holder.clEdit.visibility = View.VISIBLE
                        holder.etSex.visibility = View.INVISIBLE
                        holder.etName.visibility = View.INVISIBLE
                        holder.etLastName.visibility = View.INVISIBLE
                        holder.etEmail.visibility = View.INVISIBLE
                        holder.etPhone.visibility = View.INVISIBLE
                        holder.btLoadPhoto.visibility = View.VISIBLE
                        holder.btLoadPhoto.setOnClickListener {
                            val intent = Intent(Intent.ACTION_GET_CONTENT)
                            intent.type = "image/*"
                            val SELECT_PICTURE = 1
                            startActivityForResult(
                                Intent.createChooser(intent, "Select Picture"), SELECT_PICTURE
                            )
                            callback.onActivityResult(1, 1, data = intent)
                        }
                        holder.btSetUp.setOnClickListener {
                            setUp(contacts)
                            holder.contact.visibility = View.VISIBLE
                            holder.clEdit.visibility = View.INVISIBLE
                        }
                    }
                    holder.sex.setOnClickListener {
                        holder.contact.visibility = View.INVISIBLE
                        holder.clEdit.visibility = View.VISIBLE
                        holder.etSex.visibility = View.VISIBLE
                        holder.btLoadPhoto.visibility = View.INVISIBLE
                        holder.etName.visibility = View.INVISIBLE
                        holder.etLastName.visibility = View.INVISIBLE
                        holder.etEmail.visibility = View.INVISIBLE
                        holder.etPhone.visibility = View.INVISIBLE
                        holder.btSetUp.visibility = View.VISIBLE
                        holder.etSex.afterTextChanged {
                            contacts[position].sex = holder.etSex.text.toString()
                            holder.btSetUp.setOnClickListener {
                                setUp(contacts)
                                holder.contact.visibility = View.VISIBLE
                                holder.clEdit.visibility = View.INVISIBLE
                            }
                        }
                    }
                    holder.name.setOnClickListener {
                        holder.contact.visibility = View.INVISIBLE
                        holder.clEdit.visibility = View.VISIBLE
                        holder.etSex.visibility = View.INVISIBLE
                        holder.btLoadPhoto.visibility = View.INVISIBLE
                        holder.etName.visibility = View.VISIBLE
                        holder.etLastName.visibility = View.INVISIBLE
                        holder.etEmail.visibility = View.INVISIBLE
                        holder.etPhone.visibility = View.INVISIBLE
                        holder.btSetUp.visibility = View.VISIBLE
                        holder.etName.afterTextChanged {
                            contacts[position].name = holder.etName.text.toString()
                            holder.btSetUp.setOnClickListener {
                                setUp(contacts)
                                holder.contact.visibility = View.VISIBLE
                                holder.clEdit.visibility = View.INVISIBLE
                            }
                        }
                    }
                    holder.lastName.setOnClickListener {
                        holder.contact.visibility = View.INVISIBLE
                        holder.clEdit.visibility = View.VISIBLE
                        holder.etSex.visibility = View.INVISIBLE
                        holder.btLoadPhoto.visibility = View.INVISIBLE
                        holder.etName.visibility = View.INVISIBLE
                        holder.etLastName.visibility = View.VISIBLE
                        holder.etEmail.visibility = View.INVISIBLE
                        holder.etPhone.visibility = View.INVISIBLE
                        holder.btSetUp.visibility = View.VISIBLE
                        holder.etLastName.afterTextChanged {
                            contacts[position].lastName = holder.etLastName.text.toString()
                            holder.btSetUp.setOnClickListener {
                                setUp(contacts)
                                holder.contact.visibility = View.VISIBLE
                                holder.clEdit.visibility = View.INVISIBLE
                            }
                        }
                    }
                    holder.email.setOnClickListener {
                        holder.contact.visibility = View.INVISIBLE
                        holder.clEdit.visibility = View.VISIBLE
                        holder.etSex.visibility = View.INVISIBLE
                        holder.btLoadPhoto.visibility = View.INVISIBLE
                        holder.etName.visibility = View.INVISIBLE
                        holder.etLastName.visibility = View.INVISIBLE
                        holder.etEmail.visibility = View.VISIBLE
                        holder.etPhone.visibility = View.INVISIBLE
                        holder.btSetUp.visibility = View.VISIBLE
                        holder.etEmail.afterTextChanged {
                            contacts[position].email = holder.etEmail.text.toString()
                            holder.btSetUp.setOnClickListener {
                                setUp(contacts)
                                holder.contact.visibility = View.VISIBLE
                                holder.clEdit.visibility = View.INVISIBLE
                            }
                        }
                    }
                    holder.tel.setOnClickListener {
                        holder.contact.visibility = View.INVISIBLE
                        holder.clEdit.visibility = View.VISIBLE
                        holder.etSex.visibility = View.INVISIBLE
                        holder.btLoadPhoto.visibility = View.INVISIBLE
                        holder.etName.visibility = View.INVISIBLE
                        holder.etLastName.visibility = View.INVISIBLE
                        holder.etEmail.visibility = View.INVISIBLE
                        holder.etPhone.visibility = View.VISIBLE
                        holder.btSetUp.visibility = View.VISIBLE
                        holder.etPhone.afterTextChanged {
                            contacts[position].tel = holder.etPhone.text.toString()
                            holder.btSetUp.setOnClickListener {
                                setUp(contacts)
                                holder.contact.visibility = View.VISIBLE
                                holder.clEdit.visibility = View.INVISIBLE
                            }
                        }
                    }
                }

            }
        }

    override fun getItemCount(): Int {
        return contacts.size
         }
    }

    private fun startActivityForResult(gallery: Intent, resultLoadImage: Int) {

            }


    class ViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView){

        var photo = itemView.ivItemPhoto
        var sex = itemView.tvItemSex
        var name = itemView.tvItemName
        var lastName = itemView.tvItemLastName
        var email = itemView.tvItemEmail
        var tel = itemView.tvItemTel
        var adjust = itemView.btItemAdjust
        var delete = itemView.btItemDelete
        var fab = itemView.ibFAB
        var yes = itemView.tvDialogYes
        var no =  itemView.tvDialogNo
        var contact = itemView.clItemContact
        var clEdit = itemView.clEdit
        var etSex = itemView.etSex
        var etName = itemView.etName
        var etLastName = itemView.etLastName
        var etEmail = itemView.etEmail
        var etPhone = itemView.etPhone
        var btLoadPhoto = itemView.btLoadPhoto
        var btItemDelete = itemView.btItemDelete
        var tvDialog = itemView.tvDialog
        var tvDialogYes = itemView.tvDialogYes
        var tvDialogNo = itemView.tvDialogNo
        var tvItemAdjust = itemView.btItemAdjust
        var btSetUp = itemView.btSetUp


    }

        interface ContactsCallback{
            fun onItemSelected( index : Int)
            fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?)
    }
        fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
            this.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
            }
        })
    }