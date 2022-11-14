package com.example.testingapp1.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testingapp1.model.TaskRepository
import com.example.testingapp1.model.Todo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.json.JSONObject
import javax.inject.Inject

@HiltViewModel
class TodosViewModel @Inject constructor(
    private val repository: TaskRepository,
) : ViewModel() {
    val todos = MutableLiveData(emptyList<Todo>())

    val taskName = MutableLiveData("")
    val taskComplete = MutableLiveData(false)

    fun save() {
        viewModelScope.launch {
//            repository.save(taskName.value ?: "", taskComplete.value ?: false)
//            loadTodos()
        }
        taskName.postValue("")
        taskComplete.postValue(false)
    }

    init {
        viewModelScope.launch {
            loadTodos()
        }
    }

    private suspend fun loadTodos() {
        val ts = mutableListOf<Todo>()
        ts.add(Todo(
            name = "Buy some milk",
            complete = true
        ))
        ts.add(Todo(
            name = "Buy some fish",
            complete = false
        ))
        ts.add(Todo(
            name = "Buy some bread",
            complete = false
        ))

//        val all = repository.getAll()
//        for (i in 0 until all.length()) {
//            val todo = all[i]
//            val jsonObject = todo as JSONObject
//            ts.add(Todo(
//                name = jsonObject.get("name") as String,
//                complete = jsonObject.get("complete") as Boolean
//            ))
//        }
        todos.postValue(ts)
    }
}