package com.fan.activitytest.jetpack.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BookEntity(var name: String, var pages: Int,var author:String) {

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0

}
