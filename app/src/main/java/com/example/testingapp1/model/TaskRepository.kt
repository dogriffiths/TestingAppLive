package com.example.testingapp1.model

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONArray
import org.json.JSONObject
import ru.gildor.coroutines.okhttp.await


class TaskRepository(val baseURL: String) {
    private var client = OkHttpClient()

    suspend fun getAll(): JSONArray {
        return withContext(Dispatchers.IO) { readTodos() }
    }

    suspend fun save(name: String, complete: Boolean) {
        withContext(Dispatchers.IO) { saveTasks(name, complete) }
    }

    private suspend fun readTodos(): JSONArray {
        val builder: Request.Builder = Request.Builder()
        builder.url("$baseURL/todos")
        val request: Request = builder.build()
        try {
            val response = client.newCall(request).await()

            val string = response.body!!.string()
            val jsonArray = JSONArray(string)
            return jsonArray
        } catch (e: Exception) {
            throw RuntimeException("Unable to read todos", e)
        }
    }

    private suspend fun saveTasks(name: String, complete: Boolean) {
        val builder: Request.Builder = Request.Builder()
        builder.url("$baseURL/todos")
        val request: Request = builder
            .post(
                JSONObject(
                    mapOf<String, Any>("name" to name, "complete" to complete)
                ).toString().toRequestBody("application/json".toMediaType())
            )
            .build()
        try {
            client.newCall(request).await()
        } catch (e: Exception) {
            throw RuntimeException("Unable to save tasks", e)
        }
    }
}
