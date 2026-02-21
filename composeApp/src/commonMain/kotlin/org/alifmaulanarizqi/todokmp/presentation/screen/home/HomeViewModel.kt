package org.alifmaulanarizqi.todokmp.presentation.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import org.alifmaulanarizqi.todokmp.data.ToDoRepository
import org.alifmaulanarizqi.todokmp.util.RequestState

class HomeViewModel(
    private val repository: ToDoRepository
): ViewModel() {
    val tasks = repository.readAllTasks()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = RequestState.Loading
        )
}