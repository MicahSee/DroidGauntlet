package com.example.droidgauntlet

import android.content.Context
import org.json.JSONArray
import org.json.JSONObject

object ResultsTracker {

    private data class Task(val id: String, var success: Boolean = false)

    private val tasks = mutableListOf<Task>()

    fun init(context: Context, challengeIds: List<String>) {
        tasks.clear()
        challengeIds.forEach { tasks.add(Task(it)) }
        write(context)
    }

    fun markComplete(context: Context, id: String) {
        tasks.find { it.id == id }?.success = true
        write(context)
    }

    private fun write(context: Context) {
        val completed = tasks.count { it.success }
        val arr = JSONArray().apply {
            tasks.forEach { put(JSONObject().put("id", it.id).put("success", it.success)) }
        }
        val json = JSONObject()
            .put("all_success", completed == tasks.size && tasks.isNotEmpty())
            .put("tasks_total", tasks.size)
            .put("tasks_completed", completed)
            .put("tasks", arr)
        context.getExternalFilesDir(null)
            ?.resolve("results.json")
            ?.writeText(json.toString())
    }
}
