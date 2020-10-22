package com.example.list.ui

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.list.R
import kotlinx.android.synthetic.main.fragment_list.view.*

class ListFragment : Fragment() {

    private lateinit var viewModel: TaskViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_list, container, false)

        //Recyclerview
        val adapter = ListAdapter()
        val recyclerView = view.recycler_view
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        //viewModel
        viewModel = ViewModelProvider(this).get(TaskViewModel::class.java)
        viewModel.getAllTasks.observe(viewLifecycleOwner, Observer { task ->
            adapter.setData(task)
        })

        // Create options menu for deletion
        setHasOptionsMenu(true)


        view.floatingActionButton.setOnClickListener{
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }


        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menu_delete){
            deleteAllTasks()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteAllTasks(){
        val builder = AlertDialog.Builder(requireContext())
        // If yes then delete all
        builder.setPositiveButton("Yes"){_, _ ->
            viewModel.deleteAllTasks()
            Toast.makeText(requireContext(),"Deleted all tasks", Toast.LENGTH_SHORT).show()
        }
        // If no then nothing
        builder.setNeutralButton("No"){_, _ ->}
        builder.setTitle("Delete all tasks?")
        builder.setMessage("Are you sure you want to delete all tasks?")
        builder.create().show()
    }
}