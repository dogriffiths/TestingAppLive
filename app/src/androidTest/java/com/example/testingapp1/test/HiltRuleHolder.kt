package com.example.testingapp1.test

import com.example.testingapp1.HiltModule
import com.example.testingapp1.model.TaskRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import io.cucumber.java.Before
import io.cucumber.junit.WithJunitRule
import org.junit.Assert
import org.junit.Rule
import javax.inject.Inject
import javax.inject.Singleton

@WithJunitRule(useAsTestClassInDescription = true)
@HiltAndroidTest
class HiltRuleHolder {

    @Rule(order = 0)
    @JvmField
    val hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var repository: TaskRepository

    @Before
    fun init() {
        hiltRule.inject()
        Assert.assertEquals("http://localhost:8080", repository.baseURL)
    }

}

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [HiltModule::class]
)
class FakeHiltModule {
    @Singleton
    @Provides
    fun repository(): TaskRepository {
        return TaskRepository("http://localhost:8080")
    }
}
