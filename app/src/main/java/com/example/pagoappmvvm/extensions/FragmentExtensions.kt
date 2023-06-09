package com.example.pagoappmvvm.extensions

import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.NavGraph
import androidx.navigation.fragment.findNavController

fun Fragment.navigateToNextDestination(navDirections: NavDirections) {
    val destinationId =
        findNavController().currentDestination?.getAction(navDirections.actionId)?.destinationId

    findNavController().currentDestination?.let { node ->
        val currentNode = when (node) {
            is NavGraph -> node
            else -> node.parent
        }
        if (destinationId != null) {
            currentNode?.findNode(destinationId)?.let {
                findNavController().navigate(navDirections)
            }
        }
    }
}