package com.example.weatherappdemo.RoomDatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.weatherappdemo.Model.SearchHistory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [SearchHistory::class],version = 2,exportSchema = false)
abstract class SearchRoomDB: RoomDatabase(){
    abstract fun searchDao():SearchDao
    companion object{
        @Volatile
        private var INSTANCE:SearchRoomDB?=null

        fun getDatabase(context: Context,scope: CoroutineScope):SearchRoomDB{
            return INSTANCE?: synchronized(this){
                val instance = Room.databaseBuilder(context.applicationContext,SearchRoomDB::class.java,"search_database").fallbackToDestructiveMigration().allowMainThreadQueries().addCallback(SearchRoomDBCallback(scope)).build()
                INSTANCE =instance
                instance
            }
        }

        private class SearchRoomDBCallback(private val scope: CoroutineScope): RoomDatabase.Callback() {
            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                INSTANCE?.let { database ->
                    scope.launch(Dispatchers.IO) {
                        database.searchDao()
                } }
            }
        }
        fun clearDatabase(searchDao: SearchDao)
        {
            searchDao.deleteAll()
        }
    }
}