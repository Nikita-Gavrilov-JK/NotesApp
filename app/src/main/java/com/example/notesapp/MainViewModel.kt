package com.example.notesapp

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.notesapp.model.Note
import com.example.notesapp.utils.TYPE_FIREBASE
import com.example.notesapp.utils.TYPE_ROOM

class MainViewModel(application: Application): AndroidViewModel(application) {
    val readTest: MutableLiveData<List<Note>> by lazy {
        MutableLiveData<List<Note>>()
    }
    val dpType: MutableLiveData<String> by lazy {
        MutableLiveData<String>(TYPE_ROOM)
    }

    init {
        readTest.value =
            when(dpType.value) {
                TYPE_ROOM -> {
                    listOf<Note>(
                        Note(title = "Note 1", subtitle = "Subtitle #1"),
                        Note(title = "Note 2", subtitle = "Subtitle #2"),
                        Note(title = "Note 3", subtitle = "Subtitle #3"),
                        Note(title = "Note 4", subtitle = "Subtitle #4"),
                    )
                }
                TYPE_FIREBASE -> listOf()
                else -> listOf()
            }
    }
    fun initDatabase(type: String){
        dpType.value = type
        Log.d("checkData", "MainViewModel initDatabase type $type")
    }
}

class MainViewModelFactory(private val application: Application): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(application = application) as T
        }
        throw IllegalArgumentException("Нет ViewModel class")
    }
}