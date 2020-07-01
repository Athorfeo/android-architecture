package io.github.athorfeo.architecture.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.github.athorfeo.architecture.database.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    //--
    @Query("SELECT * FROM movies WHERE movie_id = :id LIMIT 1")
    fun getMovie(id: Int): Flow<MovieEntity>

    @Query("SELECT * FROM movies")
    fun getMovies(): Flow<List<MovieEntity>>

    //--
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(vararg movies: MovieEntity): List<Long>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(movie: MovieEntity): Long

    //--
    @Query("DELETE FROM movies")
    suspend fun deleteAll()
}