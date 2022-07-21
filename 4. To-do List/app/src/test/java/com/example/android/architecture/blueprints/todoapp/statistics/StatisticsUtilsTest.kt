package com.example.android.architecture.blueprints.todoapp.statistics

import com.example.android.architecture.blueprints.todoapp.data.Task
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Assert
import org.junit.Test

internal class StatisticsUtilsTest{
    @Test
    fun getActiveAndCompletedStats_noCompleted_returnsHundredZero(){

        val test: List<Task> = listOf<Task>(
            Task("title", "desc", isCompleted = false)
        )
        val result = getActiveAndCompletedStats(test)

        /* regular junit tests
        Assert.assertEquals(result.completedTasksPercent, 0f)
        Assert.assertEquals(result.activeTasksPercent, 100f)
        */

        //hamercrest test
        assertThat(result.completedTasksPercent, `is`(0f))
        assertThat(result.activeTasksPercent, `is` (100f))
    }
}