package com.desafiolatam.coroutines.repository

import com.desafiolatam.coroutines.data.TaskEntity
import kotlinx.coroutines.flow.Flow

/** NO MODIFICAR */
interface TaskRepository {
    suspend fun getTasks(): Flow<List<TaskEntity>>
    suspend fun addTask(task: TaskEntity)
    suspend fun deleteTask(task: TaskEntity)
    suspend fun isTaskCompleted(task: TaskEntity, isCompleted: Boolean)
}