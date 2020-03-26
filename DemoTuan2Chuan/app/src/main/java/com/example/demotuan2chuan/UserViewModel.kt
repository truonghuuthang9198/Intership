package com.example.demotuan2chuan

import android.app.Application
import android.graphics.ColorSpace
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class UserViewModel:AndroidViewModel
{

//    private val repository: UserRepository
//    val allUser: LiveData<List<UserClass>>
//
//    init {
//        val userDao = UserDataBase.getDatabase(application, viewModelScope).UserDao()
//        repository = UserRepository(userDao)
//        allUser = repository.allUser
//    }
//    fun insert(user: UserClass) = viewModelScope.launch {
//        repository.insert(UserClass("thangtruongst","123456","truonghuuthangst@gmail.com","112/33 Lê Trọng Tấn",0))
//    }



    private var userRepository: UserRepository
    private var mAllUsers: LiveData<List<UserClass>>
    constructor(application: Application) : super(application){
        userRepository = UserRepository(application)
        mAllUsers  = userRepository.getAllUsers()
    }
    fun getAllUsers(): LiveData<List<UserClass>> {
        return mAllUsers
    }
    fun insert(user: UserClass) {
        userRepository.insert(user)
    }
}