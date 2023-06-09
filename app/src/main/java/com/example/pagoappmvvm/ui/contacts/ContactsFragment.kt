package com.example.pagoappmvvm.ui.contacts

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pagoappmvvm.databinding.FragmentContactsBinding
import com.example.pagoappmvvm.extensions.observe
import com.example.pagoappmvvm.model.Contact
import com.example.pagoappmvvm.ui.common.BaseFragment
import com.example.pagoappmvvm.ui.contacts.adapter.ContactsRecyclerViewAdapter
import com.example.pagoappmvvm.utils.ContactUtils
import org.koin.androidx.viewmodel.ext.android.viewModel

class ContactsFragment : BaseFragment<FragmentContactsBinding>(FragmentContactsBinding::inflate) {
    private val contactsViewModel: ContactsViewModel by viewModel()

    override fun getVM() = contactsViewModel

    override fun FragmentContactsBinding.onViewCreated(savedInstanceState: Bundle?) {
        observe(contactsViewModel.contactsListLiveData) { list ->
            setUpContactsAdapter(list.filter { it.status == ContactUtils.CONTACT_ACTIVE_STATUS })
        }

        //offline support
        setUpContactsAdapter(contactsViewModel.getContactsFromLocal())

        contactsViewModel.getContacts()
    }

    private fun setUpContactsAdapter(contactsList: List<Contact>) {
        binding?.contactsRecyclerView?.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding?.contactsRecyclerView?.adapter =
            ContactsRecyclerViewAdapter(contactsList) { contact ->
                //TODO implement onclick listener
            }
    }

}