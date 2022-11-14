package com.example.testingapp1

import com.example.testingapp1.model.TaskRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class HiltModule {
    @Provides
    @Singleton
    fun repository(): TaskRepository {
        return TaskRepository("https://svelte-tasks-31ba2.web.app")
    }
}

