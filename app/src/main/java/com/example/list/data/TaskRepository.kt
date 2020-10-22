package com.example.list.data

import androidx.lifecycle.LiveData

class TaskRepository (private val taskDao: TaskDao) {

    val getAllTasks: LiveData<List<Task>> = taskDao.getTasks()

    suspend fun addTask(task: Task){
        taskDao.addTask(task)
    }

    suspend fun updateTask(task: Task){
        taskDao.updateTask(task)
    }

    suspend fun deleteTask(task: Task){
        taskDao.deleteTask(task)
    }

    suspend fun deleteAllTasks(){
        taskDao.deleteAllTasks()
    }
}