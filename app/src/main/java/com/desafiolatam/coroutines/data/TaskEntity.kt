package com.desafiolatam.coroutines.data

import androidx.room.Entity
import androidx.room.PrimaryKey

/** NO MODIFICAR */
@Entity(tableName = "task")
data class TaskEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val description: String? = null,
    val isCompleted: Boolean = false
)
