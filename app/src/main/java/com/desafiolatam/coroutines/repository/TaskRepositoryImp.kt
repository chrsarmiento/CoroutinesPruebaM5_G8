package com.desafiolatam.coroutines.repository

import com.desafiolatam.coroutines.data.TaskDao
import com.desafiolatam.coroutines.data.TaskEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/** NO MODIFICAR */
class TaskRepositoryImp @Inject constructor(
    private val taskDao: TaskDao
) : TaskRepository {

    override suspend fun getTasks(): Flow<List<TaskEntity>> = taskDao.getTasks()

    override suspend fun addTask(task: TaskEntity) = taskDao.addTask(task)

    override suspend fun deleteTask(task: TaskEntity) = taskDao.deleteTask(task.id)

    override suspend fun isTaskCompleted(task: TaskEntity, isCompleted: Boolean) {
        TODO("Not yet implemented")
    }
}