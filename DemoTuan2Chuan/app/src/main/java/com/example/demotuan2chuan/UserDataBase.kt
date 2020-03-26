package com.example.demotuan2chuan

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import io.reactivex.disposables.Disposable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch

@Database(entities = [UserClass::class],version = 1)
abstract class UserDataBase: RoomDatabase()
{
//    abstract fun UserDao():UserDao
//    companion object {
//        private var INSTANCE: UserDataBase? = null
//
//        fun getDatabase(
//            context: Context,
//            scope: CoroutineScope
//        ): UserDataBase {
//            return INSTANCE ?: synchronized(this) {
//                val instance = Room.databaseBuilder(
//                    context.applicationContext,
//                    UserDataBase::class.java,
//                    "tab_user"
//                ).fallbackToDestructiveMigration()
//                    .addCallback(UserDataBaseCallBack(scope))
//                    .build()
//                INSTANCE = instance
//                instance
//            }
//        }
//        private class UserDataBaseCallBack(
//            private val scope: CoroutineScope
//        ) : RoomDatabase.Callback() {
//            override fun onOpen(db: SupportSQLiteDatabase) {
//                super.onOpen(db)
//                INSTANCE?.let { database -> scope.launch(Dispatchers.IO){
//                       populateDatabase(database.UserDao())
//                }  }
//            }
//        }
//        fun populateDatabase(userDao: UserDao){
//            userDao.deleteAll()
//            var user = UserClass("truonghuuthangst","123456","truonghuuthang@gmail.com","115/22 Lê Trọng Tấn",0)
//            userDao.insert(user)
//            user = UserClass("lehuythachkh","123456","truonghuuthang@gmail.com","115/22 Lê Trọng Tấn",0)
//            userDao.insert(user)
//        }
//    }




    abstract fun userDao(): UserDao
    companion object {
        private var INSTANCE: UserDataBase? = null
        fun getInstance(context: Context): UserDataBase? {
            if (INSTANCE == null) {
                synchronized(UserDataBase::class) {
                    INSTANCE = Room.databaseBuilder(context,
                        UserDataBase::class.java, "userData.db")
                        .addCallback(sRoomDataBaseCallback)
                        .build()
                }
            }
            return INSTANCE
        }
        fun destroyInstance() {
            INSTANCE = null
        }
        val sRoomDataBaseCallback = object:RoomDatabase.Callback(){
            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                InitDBAsync(INSTANCE!!).execute()
            }
        };

    }
}



