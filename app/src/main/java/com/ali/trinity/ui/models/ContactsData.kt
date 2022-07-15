package com.ali.trinity.ui.models

import com.google.gson.annotations.SerializedName

data class ContactsData(
    @SerializedName("list")
    var contactsData: List<ContactsModel>? = null
)
