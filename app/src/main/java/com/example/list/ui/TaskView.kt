package com.example.list.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.list.R
import com.example.list.data.Task
import com.example.list.data.TaskDatabase
import com.example.list.data.TaskRepository
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.lang.StringBuilder

class TaskView : AppCompatActivity() {

    lateinit var viewModel: TaskViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        setupActionBarWithNavController(findNavController(R.id.fragment))
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.fragment)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
//TODO:
    // Fix data binding issue
    // Build Alert Window to add task
    // Implement onClickListener to add Task from Alert
    // Display Tasks in Main Screen
    // RecyclerView/ScrollView 
}