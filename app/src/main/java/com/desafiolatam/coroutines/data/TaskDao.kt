package com.desafiolatam.coroutines.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

/** NO MODIFICAR */
@Dao
interface TaskDao {

    @Query("SELECT * FROM task")
    fun getTasks(): Flow<List<TaskEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addTask(taskEntity: TaskEntity)

    @Query("DELETE FROM task WHERE id= :taskId")
    suspend fun deleteTask(taskId: Int)

    @Query("UPDATE task SET isCompleted= :isCompleted WHERE id= :taskId")
    suspend fun taskCompleted(isCompleted: Boolean, taskId: Int)

}