package io.github.athorfeo.architecture.ui.base.viewmodel

import androidx.lifecycle.ViewModel
import com.hadilq.liveevent.LiveEvent
import io.github.athorfeo.architecture.model.ErrorResource
import io.github.athorfeo.architecture.testing.OpenForTesting

@OpenForTesting
open class BaseViewModel: ViewModel() {
    val isLoading = LiveEvent<Boolean>().apply { postValue(false) }
    val isError = LiveEvent<ErrorResource>()

    fun setLoading(boolean: Boolean){ isLoading.value = boolean }
    private fun postLoading(boolean: Boolean){ isLoading.postValue(boolean) }

    fun setError(code: Int?, message: String? = null){ isError.value = ErrorResource(code, message) }
    fun postError(code: Int?, message: String? = null){ isError.postValue(ErrorResource(code, message)) }
}