package com.ahrybuk.opencvtest.data.repositories.base

import com.ahrybuk.opencvtest.data.models.Session
import io.reactivex.Observable

interface SessionRepository {

    fun getSession(timeStamp: Long): Observable<Session>

    fun saveSession(session: Session): Observable<Boolean>

    fun getSessionsList(from: Int = 0, step: Int = 10): Observable<Session>
}