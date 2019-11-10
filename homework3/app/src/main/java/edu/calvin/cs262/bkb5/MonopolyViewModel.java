package edu.calvin.cs262.bkb5;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class MonopolyViewModel extends AndroidViewModel {
    private MonopolyRepository _Repository;
    private LiveData<List<Player>> _AllPlayers;

    public MonopolyViewModel (Application application) {
        super(application);
        _Repository = new MonopolyRepository(application);
        _AllPlayers = _Repository.getAllPlayers();
    }

    LiveData<List<Player>> getAllPlayers() { return _AllPlayers; }

    public void insertPlayer(Player player) { _Repository.insertPlayer(player); }
}
