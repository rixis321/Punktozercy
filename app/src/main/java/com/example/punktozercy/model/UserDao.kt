package com.example.punktozercy.model

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertUser(user:User)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUsers(vararg users:User)

    @Update
    fun updateUser(user:User)

    @Update
    fun updateUsers(users:User)

    @Delete
    fun deleteUsers(vararg users: User)

    @Delete
    fun deleteUser(user: User)

    @Query("SELECT * FROM users")
    fun loadAllUsers(): LiveData<List<User>>

    //TODO
    //funkcjonalnosc do przypominania hasla
    @Query("SELECT EXISTS(SELECT email FROM users WHERE :email = email) ")
    fun isUserEmailExists(email:String):Boolean

    @Query("SELECT * FROM users WHERE :em = email AND :pass = password")
    fun isUserLoginExists(em: String,pass:String):List<User>

    @Query("SELECT * FROM users WHERE :token = google_token")
    fun findUserByGoogleToken(token:String):User
    @Query("SELECT EXISTS(SELECT username FROM users WHERE username = :userName )")
    fun isUserNameExists(userName:String):Boolean
}