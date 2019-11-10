package edu.calvin.cs262.bkb5;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "player_table")
public class Player {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    public final String _id;

    @NonNull
    @ColumnInfo(name = "emailAddress")
    public final String _emailAddress;

    @NonNull
    @ColumnInfo(name = "name")
    public final String _name;

    public Player(String id, String emailAddress, String name) {
        this._id = id;
        this._emailAddress = emailAddress;
        this._name = name;
    }

    public String getId() {return this._id;}
    public String getEmailAddress() {return this._emailAddress;}
    public String getName() {return this._name;}
}