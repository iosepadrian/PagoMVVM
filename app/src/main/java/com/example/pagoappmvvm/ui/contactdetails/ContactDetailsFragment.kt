package com.example.pagoappmvvm.ui.contactdetails

import android.os.Bundle
import androidx.core.view.isVisible
import com.example.pagoappmvvm.databinding.FragmentContactDetailsBinding
import com.example.pagoappmvvm.extensions.isEven
import com.example.pagoappmvvm.extensions.loadImage
import com.example.pagoappmvvm.extensions.observe
import com.example.pagoappmvvm.model.Post
import com.example.pagoappmvvm.ui.common.BaseFragment
import com.example.pagoappmvvm.utils.ContactUtils
import org.koin.androidx.viewmodel.ext.android.viewModel

class ContactDetailsFragment :
    BaseFragment<FragmentContactDetailsBinding>(FragmentContactDetailsBinding::inflate) {
    private val contactDetailsViewModel: ContactDetailsViewModel by viewModel()

    override fun getVM() = contactDetailsViewModel

    override fun FragmentContactDetailsBinding.onViewCreated(savedInstanceState: Bundle?) {
        val contact = ContactDetailsFragmentArgs.fromBundle(requireArguments()).contactArgument
        contactNameTextView.text = contact.name
        emailTextView.text = contact.email
        if (contact.id != null) {
            if (contact.id!!.isEven()) {
                setImageVisibility(false)
                profileInitialsTextView.text = ContactUtils.getInitials(contact.name)
            } else {
                setImageVisibility(true)
                profileImageView.loadImage(ContactUtils.IMAGE_URL)
            }

            //offline support
            val localPostsList = contactDetailsViewModel.getPostsFromLocal(contact.id!!)
            if (localPostsList.isNotEmpty()) {
                setUpFirstPost(localPostsList)
            }
            //if we have at least one post we display the post layout
            postLayout.isVisible = localPostsList.isNotEmpty()

            contactDetailsViewModel.getPosts(contact.id!!)
        }

        arrowBackButton.setOnClickListener {
            goToPreviousFragment()
        }

        observe(contactDetailsViewModel.postsLiveData) { list ->
            if (list.isNotEmpty()) {
                setUpFirstPost(list)
            }
            //if we have at least one post we display the post layout
            postLayout.isVisible = list.isNotEmpty()
        }
    }

    //if this method is called with true parameter, the downloaded image is displayed, otherwise the initials will be displayed
    private fun setImageVisibility(isVisible: Boolean) {
        binding?.profileInitialsTextView?.isVisible = !isVisible
        binding?.profileImageView?.isVisible = isVisible
    }

    private fun setUpFirstPost(postsList: List<Post>) {
        binding?.postTitle?.text = postsList.first().title
        binding?.postDescription?.text = postsList.first().body
    }

}