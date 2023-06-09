package com.example.pagoappmvvm.ui.contacts.adapter

import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.pagoappmvvm.databinding.ViewHolderContactsRecyclerViewBinding
import com.example.pagoappmvvm.extensions.isEven
import com.example.pagoappmvvm.extensions.loadImage
import com.example.pagoappmvvm.model.Contact
import com.example.pagoappmvvm.utils.ContactUtils

class ContactsRecyclerViewHolder(
    private val binding: ViewHolderContactsRecyclerViewBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bindData(item: Contact) {
        binding.contactNameTextView.text = item.name
        if (item.id != null) {
            if (item.id!!.isEven()) {
                setImageVisibility(false)
                binding.profileInitialsTextView.text = ContactUtils.getInitials(item.name)
            } else {
                setImageVisibility(true)
                binding.profileImageView.loadImage(ContactUtils.IMAGE_URL)
            }
        }
    }

    //if this method is called with true parameter, the downloaded image is displayed, otherwise the initials will be displayed
    private fun setImageVisibility(isVisible: Boolean) {
        binding.profileInitialsTextView.isVisible = !isVisible
        binding.profileImageView.isVisible = isVisible
    }
}