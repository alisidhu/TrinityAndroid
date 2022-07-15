package com.ali.trinity.ui.contactslist

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.ali.trinity.R
import com.ali.trinity.databinding.LayoutItemContactBinding
import com.ali.trinity.ui.base.BaseBindingRecyclerAdapter
import com.ali.trinity.ui.interfaces.OnItemClickListener
import com.ali.trinity.ui.models.ContactsModel

class ContactsAdapter(
    private val list: MutableList<ContactsModel>,
) : BaseBindingRecyclerAdapter<ContactsModel, ContactsAdapter.ContactsItemViewHolder>(list) {
    override fun onCreateViewHolder(binding: ViewDataBinding): ContactsItemViewHolder {
        return ContactsItemViewHolder(binding as LayoutItemContactBinding)
    }

    override fun getLayoutIdForViewType(viewType: Int): Int = R.layout.layout_item_contact

    override fun onBindViewHolder(
        holder: ContactsItemViewHolder,
        position: Int
    ) {
        super.onBindViewHolder(holder, position)
        holder.onBind(list[position], position, onItemClickListener)
    }

    inner class ContactsItemViewHolder(private val binding: LayoutItemContactBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(
            data: ContactsModel,
            position: Int,
            onItemClickListener: OnItemClickListener?
        ) {
            binding.viewModel =
                ContactItemModel(
                    data,
                    position,
                    onItemClickListener
                )
            binding.executePendingBindings()
        }

    }

}