package com.nikonsoft.clientappkotlin

import androidx.lifecycle.ViewModel
import java.util.*

class TaskViewModel : ViewModel() {
    var todoTaskList = LinkedList<Task>()
    var doingTaskList = LinkedList<Task>()
    var doneTaskList = LinkedList<Task>()
}