package com.ahrybuk.opencvtest.db.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.ahrybuk.opencvtest.db.entities.SessionEntity
import io.reactivex.Observable

@Dao
interface SessionDao {
    @Query("SELECT * FROM sessions")
    fun getAll(): Observable<List<SessionEntity>>

    @Query("SELECT * FROM sessions WHERE timestamp IS :timestamp")
    fun getSingleByTimestamp(timestamp: Long): Observable<SessionEntity>

    @Insert
    fun insert(sessionEntity: SessionEntity): Long
}