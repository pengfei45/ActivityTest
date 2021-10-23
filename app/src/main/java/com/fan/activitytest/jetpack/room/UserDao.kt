package com.fan.activitytest.jetpack.room

import androidx.room.*

@Dao
interface UserDao {

    @Insert
    fun insertUser(userEntity: UserEntity): Long

    @Update
    fun updateUser(newUser: UserEntity)

    @Delete
    fun deleteUser(userEntity: UserEntity)

    @Query("select * from UserEntity")
    fun loadAllUsers(): List<UserEntity>

    @Query("select * from UserEntity where age > :age")
    fun loadUsersOlderThan(age: Int): List<UserEntity>

    @Query("delete from UserEntity where lastName = :lastName")
    fun deleteUserByLastName(lastName: String): Int
}