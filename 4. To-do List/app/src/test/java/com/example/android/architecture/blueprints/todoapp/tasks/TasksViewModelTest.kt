package com.example.android.architecture.blueprints.todoapp.tasks

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.android.architecture.blueprints.todoapp.data.Task
import com.example.android.architecture.blueprints.todoapp.data.source.FakeTestRepository
import com.example.android.architecture.blueprints.todoapp.getOrAwaitValue
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


// testes que precisam do contexto da aplicação, como view model, podem ser testados com AndroidX

private lateinit var taskViewModel: TasksViewModel

internal class TasksViewModelTest{


    @get: Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    //lateinit + setup para uma variável que será utilizada várias vezes
    private lateinit var tasksRepository: FakeTestRepository

    @Before
    fun setupViewModel(){
        tasksRepository = FakeTestRepository()
        val task1 = Task("Title1", "Description1")
        val task2 = Task("Title2", "Description2", true)
        val task3 = Task("Title3", "Description3", true)
        tasksRepository.addTasks(task1, task2, task3)

        taskViewModel = TasksViewModel(tasksRepository)

    }

    @Test
    fun addNewTask_setsNewTaskEvent(){
        taskViewModel.addNewTask()
        val value = taskViewModel.newTaskEvent.getOrAwaitValue()
        assertThat(value.getContentIfNotHandled(), not(nullValue()))
    }

    @Test
    fun setFilterAllTasks_tasksAddViewVisible(){
        val filterAllTasks = taskViewModel.setFiltering(TasksFilterType.ALL_TASKS)
        assertThat(taskViewModel.tasksAddViewVisible.getOrAwaitValue(), `is` (true))
    }

}