package com.example.list.ui

import android.app.AlertDialog
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.list.R
import com.example.list.data.Task
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*

class UpdateFragment : Fragment() {

    private val args by navArgs<UpdateFragmentArgs>()

    private lateinit var viewModel: TaskViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update, container, false)

        viewModel = ViewModelProvider(this).get(TaskViewModel::class.java)

        view.updateTask.setText(args.currentTask.name)
        view.updateButton.setOnClickListener {
            updateTask()
        }

        // Add options menu to delete tasks
        setHasOptionsMenu(true)

        return view
    }

    private fun updateTask(){
        val taskName = updateTask.text.toString()

        if(inputCheck(taskName)){
            // Create updated Task
            val updatedTask = Task(args.currentTask.id, taskName)
            // Update database
            viewModel.updateTask(updatedTask)
            // Navigate back
            Toast.makeText(requireContext(), "Updated task successfully", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        } else {
            Toast.makeText(requireContext(), "Please fill out task field", Toast.LENGTH_SHORT).show()
        }
    }

    //Checks if the Edit Text Field was empty
    private fun inputCheck(taskName: String): Boolean{
        return !TextUtils.isEmpty(taskName)

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menu_delete){
            deleteTask()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteTask(){
        val builder = AlertDialog.Builder(requireContext())
        // If yes then task gets deleted
        builder.setPositiveButton("Yes"){_, _ ->
            viewModel.deleteTask(args.currentTask)
            Toast.makeText(requireContext(), "Task deleted successfully", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
        // If no then nothing happens
        builder.setNeutralButton("No"){_, _ -> }
        builder.setTitle("Delete task?")
        builder.setMessage("Are you sure?")
        builder.create().show()
    }
}