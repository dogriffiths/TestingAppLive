package com.example.testingapp1.ui.todoscompose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.testingapp1.viewmodels.TodosViewModel

@Composable
fun TodoEditor() {
    val vm: TodosViewModel = viewModel()
    val todos by vm.todos.observeAsState()
    val taskName by vm.taskName.observeAsState()
    val taskComplete by vm.taskComplete.observeAsState()

    Column {
        taskName?.let {
            TextField(
                modifier = Modifier.testTag("taskName").fillMaxWidth(),
                value = it,
                onValueChange = {
                    vm.taskName.postValue(it)
                }
            )
        }
        taskComplete?.let {
            Row {
                Checkbox(
                    modifier = Modifier.testTag("taskComplete"),
                    checked = it,
                    onCheckedChange = { vm.taskComplete.postValue(it) }
                )
                Text("Complete?")
            }
        }
        Button(
            modifier = Modifier.testTag("save"),
            onClick = {vm.save()}
        ) {
            Text("ADD")
        }
        TodoRows(todos)
    }
}

