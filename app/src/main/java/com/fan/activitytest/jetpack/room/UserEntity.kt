package com.fan.activitytest.jetpack.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserEntity(var firstName: String, var lastName: String, var age: Int) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}
