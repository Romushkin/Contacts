package com.example.contacts

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PersonViewModel (application: Application) : AndroidViewModel(application){
    private val repository: PersonRepository
    val persons: LiveData<List<Person>>

    init {
        val dao = PersonsDataBase.getDataBase(application).getPersonDao()
        repository = PersonRepository(dao)
        persons = repository.persons
    }

    fun deletePerson(person: Person) = viewModelScope.launch(Dispatchers.IO){
        repository.delete(person)
    }

    fun insertPerson(person: Person) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(person)
    }
}