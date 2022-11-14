package com.example.testingapp1.ui.todos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.testingapp1.R
import com.example.testingapp1.databinding.FragmentTodosBinding
import com.example.testingapp1.model.Todo
import com.example.testingapp1.viewmodels.TodosViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TodosFragment : Fragment() {

    private var _binding: FragmentTodosBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(TodosViewModel::class.java)

        _binding = FragmentTodosBinding.inflate(inflater, container, false)
        val root: View = binding.root
        binding.vm = homeViewModel
        binding.lifecycleOwner = this

        homeViewModel.todos.observe(viewLifecycleOwner) {
            binding.todosList.adapter = TodoListAdapter(it)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}


class TodoListAdapter(val todos: List<Todo>) :
    RecyclerView.Adapter<TodoListAdapter.TodoViewHolder>() {

    // Describes an item view and its place within the RecyclerView
    class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val todoNameView: TextView = itemView.findViewById(R.id.todo_view_name)
        private val todoCompleteView: CheckBox = itemView.findViewById(R.id.todo_view_complete)

        fun bind(todo: Todo) {
            todoNameView.text = todo.name
            todoCompleteView.setChecked(todo.complete)
        }
    }

    // Returns a new ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.todo_view, parent, false)

        return TodoViewHolder(view)
    }

    // Returns size of data list
    override fun getItemCount(): Int {
        return todos.size
    }

    // Displays data at a certain position
    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.bind(todos[position])
    }
}