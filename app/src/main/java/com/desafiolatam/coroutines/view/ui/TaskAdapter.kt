package com.desafiolatam.coroutines.view.ui

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.desafiolatam.coroutines.data.TaskEntity
import com.desafiolatam.coroutines.databinding.ItemTaskBinding

class TaskAdapter :
    RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    private lateinit var binding: ItemTaskBinding
    lateinit var taskList: List<TaskEntity>

    lateinit var onLongClickListener: ((TaskEntity) -> Unit)
    lateinit var onCheckListener: ((TaskEntity, Boolean) -> Unit)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        binding = ItemTaskBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TaskViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.onBind(taskList[position])
    }

    override fun getItemCount(): Int = taskList.size

    inner class TaskViewHolder(binding: ItemTaskBinding) : ViewHolder(binding.root) {
        fun onBind(task: TaskEntity) {
            binding.run {
                tvTaskTitle.text = task.title
                tvTaskDescription.text = task.description

                clItem.setOnLongClickListener {
                    onLongClickListener.invoke(task)
                    false
                }

                cbIsCompleted.setOnCheckedChangeListener { _, checked ->
                    onCheckListener.invoke( task, checked)
                }

                if(task.isCompleted){
                    tvTaskTitle.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
                    cbIsCompleted.isChecked = true
                }
                else{
                    cbIsCompleted.isChecked = false
                }
            }
        }
    }
}