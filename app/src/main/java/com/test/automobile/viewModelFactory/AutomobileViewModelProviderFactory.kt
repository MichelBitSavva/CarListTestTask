package com.test.automobile.viewModelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.test.automobile.repository.AutomobileRepository
import com.test.automobile.viewModel.AutomobileViewModel

class AutomobileViewModelProviderFactory (
    val automobileRepository: AutomobileRepository
): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AutomobileViewModel(automobileRepository) as T
    }
}