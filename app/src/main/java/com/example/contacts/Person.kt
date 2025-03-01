package com.example.contacts

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "persons_table")
data class Person(
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "phone") var phone: String,
    @ColumnInfo(name = "created_at") val createdAt: Long = System.currentTimeMillis()
) {

    @PrimaryKey(autoGenerate = true)
    var id = 0

}