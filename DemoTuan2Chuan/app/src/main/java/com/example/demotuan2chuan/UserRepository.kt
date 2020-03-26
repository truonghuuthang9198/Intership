package com.example.demotuan2chuan

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData

class UserRepository
{
//    val allUser: LiveData<List<UserClass>> = userdao.getUserList()
//    suspend fun insert(userClass: UserClass)
//    {
//        userdao.insert(userClass)
//    }



    private   var userDao: UserDao
    private  var mAllUsers: LiveData<List<UserClass>>
    constructor(application: Application){
        val db = UserDataBase.getInstance(application)
        userDao = db!!.userDao()
        mAllUsers = userDao.getAllUsers()
    }
    fun getAllUsers(): LiveData<List<UserClass>> {
        return mAllUsers
    }
    fun insert(user: UserClass) {
        InsertAsyncTask(userDao).execute(user)
    }
    class InsertAsyncTask internal  constructor(userDao: UserDao): AsyncTask<UserClass, Void, Void>(){
        private  var mAsyncUserDao: UserDao
        init {
            mAsyncUserDao = userDao
        }
        override fun doInBackground(vararg p0: UserClass): Void? {
            mAsyncUserDao.insert(p0[0])
            return null
        }
    }
}