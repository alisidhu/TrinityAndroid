package com.ali.trinity.ui.contactslist

import android.content.Context
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ali.trinity.ui.models.ContactsData
import com.ali.trinity.ui.models.ContactsModel
import com.ali.trinity.ui.utils.Utils
import com.google.gson.Gson
import java.io.IOException

class MainViewModel : ViewModel() {
    var contactsList: MutableLiveData<List<ContactsModel>?> = MutableLiveData()
    var contactsAdapter: ContactsAdapter =
        ContactsAdapter(mutableListOf())
    @JvmName("getContactsList1")
    fun getContactsList(): MutableLiveData<List<ContactsModel>?> {

        return contactsList
    }

    fun loadContacts(context: Context) {
        try {
            var gson = Gson()

            val contacts: String = Utils.loadJSONFromAsset(context, "data.json")
            var  contactData:ContactsData = gson.fromJson(contacts, ContactsData::class.java)
            contactsList.postValue(contactData.contactsData)
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
    private fun updateContact(contact : ContactsModel) {
    }

}