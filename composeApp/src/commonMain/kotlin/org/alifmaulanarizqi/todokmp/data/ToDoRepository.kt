package org.alifmaulanarizqi.todokmp.data

import kotlinx.coroutines.flow.Flow
import org.alifmaulanarizqi.todokmp.domain.ToDoTask
import org.alifmaulanarizqi.todokmp.util.RequestState
import kotlin.coroutines.CoroutineContext

interface ToDoRepository {
    fun createTask(task: ToDoTask): RequestState<Unit>
    fun updateTask(task: ToDoTask): RequestState<Unit>
    fun readSelectedTask(taskId: String): RequestState<ToDoTask>
    fun readAllTasks(context: CoroutineContext): Flow<RequestState<List<ToDoTask>>>
    fun removeTask(taskId: String): RequestState<Unit>
}