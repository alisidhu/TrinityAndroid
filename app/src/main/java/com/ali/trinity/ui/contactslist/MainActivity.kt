package com.ali.trinity.ui.contactslist
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.ali.trinity.databinding.ActivityMainBinding
import com.ali.trinity.ui.interfaces.OnItemClickListener
import com.ali.trinity.ui.models.ContactsData

class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels()
    lateinit var rvContact : RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        setContentView(binding.root)
        rvContact = binding.rvContact
        loadContactsObserver()
    }
    private val rvItemClickListener = object : OnItemClickListener {
        override fun onItemClick(view: View, data: Any, pos: Int) {
            if (data is ContactsData) {
                //setResult(data)
            }
        }

    }

    private fun setupRecyclerView(){
        viewModel.contactsAdapter.onItemClickListener = rvItemClickListener

    }
    private fun loadContactsObserver(){
        setupRecyclerView()
        viewModel.loadContacts(this)
        viewModel.getContactsList().observe(this, Observer { contacts ->
            contacts?.let {
                viewModel.contactsAdapter.setList(it)
                rvContact.adapter = viewModel.contactsAdapter
            }
        })
    }

}