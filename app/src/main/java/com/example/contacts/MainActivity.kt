package com.example.contacts

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.contacts.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), PersonAdapter.PersonClickListener {

    private lateinit var viewModel: PersonViewModel
    private var db: PersonsDataBase? = null
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        binding.mainActivityRecyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = PersonAdapter(this, this)
        binding.mainActivityRecyclerView.adapter = adapter

        viewModel = ViewModelProvider(
            this, ViewModelProvider.AndroidViewModelFactory
                .getInstance(application)
        )[PersonViewModel::class.java]
        viewModel.persons.observe(this, Observer { list -> list?.let { adapter.updateList(it) } })

    }

    override fun onItemClicked(person: Person) {
        viewModel.deletePerson(person)
        Toast.makeText(this, "${person.name} удален", Toast.LENGTH_LONG).show()

    }

    fun saveData(view: View) {
        val personName = binding.mainActivityNameEditTextET.text.toString()
        val phone = binding.mainActivityPhoneEditTextET.text.toString()

        if (personName.isNotEmpty() && phone.isNotEmpty()) {
            viewModel.insertPerson(Person(personName, phone))
        }

        binding.mainActivityNameEditTextET.text.clear()
        binding.mainActivityPhoneEditTextET.text.clear()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.exitMenu) finishAffinity()
        return super.onOptionsItemSelected(item)
    }
}