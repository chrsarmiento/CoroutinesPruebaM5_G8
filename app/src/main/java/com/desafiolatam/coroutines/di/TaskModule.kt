package com.desafiolatam.coroutines.di

import android.content.Context
import androidx.room.Room
import com.desafiolatam.coroutines.data.TaskDao
import com.desafiolatam.coroutines.data.TaskDataBase
import com.desafiolatam.coroutines.view.ui.TaskAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/** NO MODIFICAR */
@InstallIn(SingletonComponent::class)
@Module
object TaskModule {

    @Provides
    @Singleton
    fun provideTaskDataBase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, TaskDataBase::class.java, "task_db")
            .createFromAsset("task.db")
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    fun provideTaskDao(db: TaskDataBase): TaskDao = db.taskDao()

    @Provides
    fun provideAdapter(): TaskAdapter = TaskAdapter()
}