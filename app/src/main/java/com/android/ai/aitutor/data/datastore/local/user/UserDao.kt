package com.android.ai.aitutor.data.datastore.local.user

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.android.ai.aitutor.domain.entities.User

@Dao
interface UserDao {

    @Insert
    suspend fun addUser(user: User)

    @Query("SELECT * FROM User WHERE id = :id")
    suspend fun getUserById(id: Int): User?

    @Query("UPDATE User SET name = :name, gender = :gender WHERE id = :id")
    suspend fun updateUserById(id: Int, name: String, gender: String)
}