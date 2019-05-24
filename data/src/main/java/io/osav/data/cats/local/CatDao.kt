package io.osav.data.cats.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import io.osav.data.cats.local.model.CatRoom
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Single

@Dao
interface CatDao {

    @Insert
    fun insert(info: CatRoom)

    @Insert
    fun insert(info: List<CatRoom>)

    @Query("SELECT * FROM catroom")
    fun getAll(): Single<List<CatRoom>>

    @Query("SELECT * FROM catroom WHERE id == :id")
    fun getById(id: String): Maybe<CatRoom>

    @Query("SELECT * FROM catroom")
    fun observeAll(): Flowable<List<CatRoom>>

    //When there is no entity in the database and the query returns no rows,
    //the Flowable will not emit, neither onNext, nor onError.
    @Query("SELECT * FROM catroom WHERE id == :id")
    fun observeById(id: String): Flowable<CatRoom>

    @Query("DELETE FROM catroom")
    fun clearAll()
}