package com.example.demotuan2chuan

import android.os.AsyncTask

public class InitDBAsync(db:UserDataBase): AsyncTask<Void, Void, Void>() {
    private val  mDao: UserDao
    init{
        mDao =  db.userDao()
    }
    override fun doInBackground(vararg params: Void): Void? {
        mDao.deleteAll()
        var user = UserClass("Chandra", "SW","1","2",1 )
        mDao.insert(user)
        user = UserClass("Mohan", "student","1","1",0)
        mDao.insert(user)
        return null
    }
}