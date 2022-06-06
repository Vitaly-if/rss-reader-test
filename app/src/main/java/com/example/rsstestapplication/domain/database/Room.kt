package com.example.rsstestapplication.domain.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*
import io.reactivex.Flowable


@Dao
interface RssDao {
    @Query("select * from RssDataBase")
    fun getRsses(): Flowable<List<RssDataBase>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg rsses: RssDataBase)

    @Query("delete from RssDataBase")
    fun clearAll()
}

@Database(entities = [RssDataBase::class], version = 1)
abstract class RssesDatabase : RoomDatabase() {
    abstract val rssDao: RssDao
}

private lateinit var INSTANCE: RssesDatabase

fun getDatabase(context: Context): RssesDatabase {
    synchronized(RssesDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(context.applicationContext,
                RssesDatabase::class.java,
                "rsses").build()
        }
    }
    return INSTANCE
}
