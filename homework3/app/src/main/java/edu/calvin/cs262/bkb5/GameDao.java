package edu.calvin.cs262.bkb5;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface GameDao {

    @Insert
    void insertGame(Game... games);

    @Query("SELECT * FROM Game")
    LiveData<List<Game>> getAllGames();
}