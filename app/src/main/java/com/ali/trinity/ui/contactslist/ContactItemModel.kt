package com.ali.trinity.ui.contactslist

import com.ali.trinity.ui.interfaces.OnItemClickListener
import com.ali.trinity.ui.models.ContactsModel

class ContactItemModel(
    val contact: ContactsModel,
    val position: Int,
    private val onItemClickListener: OnItemClickListener?
)