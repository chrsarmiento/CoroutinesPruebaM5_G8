package com.desafiolatam.coroutines.view.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.desafiolatam.coroutines.data.TaskEntity
import com.desafiolatam.coroutines.databinding.ActivityNewTaskBinding
import com.desafiolatam.coroutines.view.TaskViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class NewTaskActivity : AppCompatActivity() {

    lateinit var binding: ActivityNewTaskBinding
    private val viewModel: TaskViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAddTask.setOnClickListener {
            addTask()
            finish()
        }
    }

    private fun addTask(){
        lifecycleScope.launchWhenCreated {
            val titulo = binding.etTituloTask.text.toString()
            val descripcion = binding.etDescripcionTask.text.toString()
            viewModel.addTask(
                TaskEntity(id=0, title = titulo, description = descripcion)
            )
        }
        Toast.makeText(this,"Tarea guardada",Toast.LENGTH_SHORT).show()
    }
}
