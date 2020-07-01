package io.github.athorfeo.architecture.utils

import androidx.fragment.app.FragmentManager
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import io.github.athorfeo.architecture.R

fun FragmentManager.findNavController(): NavController {
    findFragmentById(R.id.nav_host_fragment)?.let {
        if(it is NavHostFragment){
            return it.navController
        }else{
            throw IllegalStateException("Activity does not have a NavHostFragment")
        }
    } ?: run { throw IllegalStateException("Nav host fragment not found!") }
}