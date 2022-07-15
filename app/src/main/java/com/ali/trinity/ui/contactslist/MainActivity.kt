package com.ali.trinity.ui.contactslist
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.ali.trinity.R
import com.ali.trinity.databinding.ActivityMainBinding
import com.ali.trinity.ui.interfaces.OnItemClickListener
import com.ali.trinity.ui.models.ContactsData
import com.ali.trinity.ui.models.ContactsModel

class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        setContentView(binding.root)
        loadContactsObserver()
    }
    private val rvItemClickListener = object : OnItemClickListener {
        override fun onItemClick(view: View, data: Any, pos: Int) {
            if (data is ContactsData) {
                //setResult(data)
            }
        }

    }

    private fun setupRecylerView(){
        viewModel.contactsAdapter.onItemClickListener = rvItemClickListener

    }
    private fun loadContactsObserver(){
        setupRecylerView()
        viewModel.loadContacts(this)
        viewModel.getContactsList().observe(this, Observer { contacts ->
            contacts?.let {
                viewModel.contactsAdapter.setList(it)
            }
        })
    }

}