package edu.calvin.cs262.bkb5;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Game {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    public final String _id;

    @NonNull
    @ColumnInfo(name = "time")
    public final String _time;

    public Game(String id, String time) {
        this._id = id;
        this._time = time;
    }

    public String getId() {return this._id;}
    public String getTime() {return this._time; }
}
