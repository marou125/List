package com.example.list.ui

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.list.R
import com.example.list.data.Task
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*
import kotlinx.android.synthetic.main.fragment_list.view.*


class AddFragment : Fragment() {

    private lateinit var viewModel: TaskViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add, container, false)

        viewModel = ViewModelProvider(this).get(TaskViewModel::class.java)

        view.button.setOnClickListener{
            insertDataToDatabase()
        }

        return view
    }

    //Insert new Task
    private fun insertDataToDatabase() {
        //Get text from Edit Text field
        val taskName = addNewTask.text.toString()

        if(inputCheck(taskName)){
            //Create Task object
            val task = Task(0,taskName)
            //Add data
            viewModel.addTask(task)
            Toast.makeText(requireContext(), "Successfully added to database", Toast.LENGTH_SHORT).show()
            //Navigate back to list
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        } else {
            Toast.makeText(requireContext(), "Please enter task name", Toast.LENGTH_SHORT).show()
        }

    }

    //Checks if the Edit Text Field was empty
    private fun inputCheck(taskName: String): Boolean{
        return !TextUtils.isEmpty(taskName)

    }

}