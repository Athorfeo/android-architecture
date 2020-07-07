package io.github.athorfeo.architecture.ui.fragment

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import io.github.athorfeo.architecture.R
import io.github.athorfeo.architecture.databinding.DialogLoadingBinding
import io.github.athorfeo.architecture.di.annotation.Injectable
import javax.inject.Inject

open class BaseFragment : Fragment(), Injectable {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private var loadingDialog: AlertDialog? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.let{
            val binding: DialogLoadingBinding = DataBindingUtil.inflate(
                it.layoutInflater,
                R.layout.dialog_loading,
                it.findViewById(android.R.id.content),
                false
            )

            loadingDialog = MaterialAlertDialogBuilder(it)
                .setBackground(ColorDrawable(it.getColor(android.R.color.transparent)))
                .setView(binding.root)
                .create()
        }
    }

    override fun onDetach() {
        loadingDialog?.dismiss()
        super.onDetach()
    }

    protected fun setLoading(isLoading: Boolean){
        if(isLoading){
            loadingDialog?.show()
        }else{
            loadingDialog?.hide()
        }
    }
}