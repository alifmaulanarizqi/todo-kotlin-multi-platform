package org.alifmaulanarizqi.todokmp.presentation.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import org.alifmaulanarizqi.todokmp.data.ToDoRepository
import org.alifmaulanarizqi.todokmp.domain.Priority
import org.alifmaulanarizqi.todokmp.domain.ToDoTask
import org.alifmaulanarizqi.todokmp.util.RequestState

class HomeViewModel(
    private val repository: ToDoRepository
): ViewModel() {
    private var _searchQuery = MutableStateFlow<String>("")
    val searchQuery = _searchQuery

    private var _prioritySort = MutableStateFlow(Priority.None)
    val prioritySort = _prioritySort

    val tasks = combine(
        repository.readAllTasks(viewModelScope.coroutineContext),
        prioritySort,
        _searchQuery
    ) { tasks, priority, query ->
        when(tasks) {
            is RequestState.Success -> {
                val filteredTasks = tasks.data
                    .let { list ->
                        if(priority == Priority.None) list
                        else list.filter { it.priority == priority }
                    }
                    .let { list ->
                    query.let {
                        if(query.isBlank()) list
                        else list.filter { task ->
                            task.title.contains(query, ignoreCase = true)
                        }
                    }
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

    fun updatePriorityFilter(value: Priority) {
        _prioritySort.value = value
    }
}