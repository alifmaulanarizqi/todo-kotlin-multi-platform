package org.alifmaulanarizqi.todokmp.presentation.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import org.alifmaulanarizqi.todokmp.data.ToDoRepository
import org.alifmaulanarizqi.todokmp.domain.ToDoTask
import org.alifmaulanarizqi.todokmp.util.RequestState

class HomeViewModel(
    private val repository: ToDoRepository
): ViewModel() {
    private var _searchQuery = MutableStateFlow<String?>(null)
    val searchQuery = _searchQuery

    val tasks = combine(
        repository.readAllTasks(),
        _searchQuery
    ) { tasks, query ->
        when(tasks) {
            is RequestState.Success -> {
                val filteredTasks = tasks.data.let { list ->
                    query?.let {
                        if(query.isBlank()) list
                        else list.filter { task ->
                            task.title.contains(query, ignoreCase = true)
                        }
                    } ?: list
                }.sortedByDescending { it.priority.ordinal }

                RequestState.Success(filteredTasks)
            }
            else -> tasks
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = RequestState.Loading
    )

    fun markTestAsCompleted(task: ToDoTask): RequestState<Unit> {
        return repository.updateTask(task)
    }

    fun removeTask(taskId: String): RequestState<Unit> {
        return repository.removeTask(taskId)
    }

    fun updateSearchQuery(value: String) {
        _searchQuery.value = value
    }
}