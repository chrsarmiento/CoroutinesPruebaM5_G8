package com.desafiolatam.coroutines.view.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.desafiolatam.coroutines.data.TaskEntity
import com.desafiolatam.coroutines.databinding.ActivityMainBinding
import com.desafiolatam.coroutines.view.TaskViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private val viewModel: TaskViewModel by viewModels()

    @Inject
    lateinit var adapter: TaskAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getTaskList()

        binding.fabAddTask.setOnClickListener {
            startActivity(Intent(this,NewTaskActivity::class.java))
        }

    }

    private fun getTaskList() {
        lifecycleScope.launchWhenResumed {
            viewModel.taskListStateFlow.collectLatest {
                initRecyclerView(it)
            }
        }
    }

    private fun initRecyclerView(taskList: List<TaskEntity>) {
        adapter = TaskAdapter()
        adapter.taskList = taskList
        binding.rvTask.layoutManager = LinearLayoutManager(this)
        binding.rvTask.adapter = adapter
        adapter.onLongClickListener = {
            deleteTask(it)
        }
        adapter.onCheckListener = {
            task, isCompleted -> taskCompleted(task,isCompleted)
        }
    }

    private fun deleteTask(task: TaskEntity){
        lifecycleScope.launchWhenCreated {
            viewModel.deleteTask(task)
        }
    }

    private fun taskCompleted(task: TaskEntity, isCompleted: Boolean){
        lifecycleScope.launchWhenCreated {
            viewModel.markTaskCompleted(task,isCompleted)
        }
    }





}