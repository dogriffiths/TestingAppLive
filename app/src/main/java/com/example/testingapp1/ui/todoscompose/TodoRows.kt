package com.example.testingapp1.ui.todoscompose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import com.example.testingapp1.model.Todo

@Composable
fun TodoRows(todos: List<Todo>?) {
    LazyColumn(
        modifier = Modifier.testTag("Rows")
    ) {
        items(todos?.size ?: 0) {
            val todo = todos?.get(it)
            if (todo != null) {
                Row(
                    modifier = Modifier.fillMaxWidth().testTag("Row-$it"),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(
                        modifier = Modifier.testTag("name"),
                        text = todo.name
                    )
                    Checkbox(
                        modifier = Modifier.testTag("complete"),
                        checked = todo.complete,
                        onCheckedChange = {}
                    )
                }
            }
        }
    }
}