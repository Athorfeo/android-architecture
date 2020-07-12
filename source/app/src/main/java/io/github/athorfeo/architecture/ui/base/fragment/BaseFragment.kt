package io.github.athorfeo.architecture.ui.base.fragment

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import io.github.athorfeo.architecture.R
import io.github.athorfeo.architecture.databinding.DialogLoadingBinding
import io.github.athorfeo.architecture.di.annotation.Injectable
import io.github.athorfeo.architecture.model.ErrorResource
import io.github.athorfeo.architecture.ui.base.viewmodel.BaseViewModel
import io.github.athorfeo.architecture.utils.error.AppCode
import timber.log.Timber
import java.net.HttpURLConnection
import javax.inject.Inject

open class BaseFragment : Fragment(), Injectable {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private var loadingDialog: AlertDialog? = null
    private var errorDialog: AlertDialog? = null

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

            errorDialog = MaterialAlertDialogBuilder(it).create()
        }
    }

    override fun onDetach() {
        loadingDialog?.dismiss()
        super.onDetach()
    }

    protected fun initializeFragment(viewModel: BaseViewModel){
        viewModel.isLoading.observe(viewLifecycleOwner, Observer {
            setLoading(it)
        })

        viewModel.isError.observe(viewLifecycleOwner, Observer {
            it.process()
        })
    }

    protected fun setLoading(isLoading: Boolean){
        if(isLoading){
            loadingDialog?.show()
        }else{
            loadingDialog?.hide()
        }
    }

    private fun showError(message: String, title: String = getString(R.string.error_title_alert)){
        errorDialog?.apply {
            setTitle(title)
            setMessage(message)
            setButton(AlertDialog.BUTTON_POSITIVE, getString(R.string.action_accept)){ dialog, _ ->
                dialog.dismiss()
            }
            show()
        }
    }

    protected fun ErrorResource.process(){
        Timber.tag("AthorArch").e("Code: $code - Message: $message")

        when(this.code){
            /* HTTP ERROR */
            HttpURLConnection.HTTP_INTERNAL_ERROR,
            HttpURLConnection.HTTP_UNAVAILABLE,
            HttpURLConnection.HTTP_VERSION -> {
                showError(getString(R.string.error_text_http_internal_error))
            }

            HttpURLConnection.HTTP_NOT_FOUND -> {
                showError(getString(R.string.error_text_http_not_found))
            }

            /* SYSTEM ERROR */
            AppCode.NO_INTERNET -> {
                showError(getString(R.string.error_text_no_internet))
            }

            /* DATA ERROR */
            AppCode.DATA_EMPTY -> {
                showError(getString(R.string.error_text_data_empty))
            }

            /* Default Message */
            else -> {
                showError(getString(R.string.error_text_default))
            }
        }
    }
}