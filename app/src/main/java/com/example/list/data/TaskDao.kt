package com.example.list.data

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addTask(task: Task)

    @Query("SELECT * FROM task_table")
    fun getTasks(): LiveData<List<Task>>

    @Update
    suspend fun updateTask(task: Task)

    @Delete
    suspend fun deleteTask(task: Task)

    // Delete all tasks
    @Query("DELETE FROM task_table")
    suspend fun deleteAllTasks()
}