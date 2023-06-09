package com.example.pagoappmvvm.ui.contacts.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pagoappmvvm.databinding.ViewHolderContactsRecyclerViewBinding
import com.example.pagoappmvvm.model.Contact

class ContactsRecyclerViewAdapter(
    private val items: List<Contact>,
    private val listener: (contact: Contact) -> Unit
) : RecyclerView.Adapter<ContactsRecyclerViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactsRecyclerViewHolder {
        return ContactsRecyclerViewHolder(
            ViewHolderContactsRecyclerViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ContactsRecyclerViewHolder, position: Int) {
        val item = items[position]
        holder.bindData(item)
        holder.itemView.setOnClickListener {
            listener.invoke(item)
        }
    }

    override fun getItemCount(): Int = items.size
}