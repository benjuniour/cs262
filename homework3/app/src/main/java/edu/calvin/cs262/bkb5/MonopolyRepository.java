package edu.calvin.cs262.bkb5;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class MonopolyRepository {
    private PlayerDao _PlayerDao;
    private LiveData<List<Player>> _AllPlayers;
    private GameDao _GameDao;
    private LiveData<List<Game>> _AllGames;

    MonopolyRepository(Application application) {
        MonopolyRoomDatabase db = MonopolyRoomDatabase.getDatabase(application);
        _PlayerDao = db.playerDao();
        _AllPlayers = _PlayerDao.getAllPlayers();

        _GameDao = db.gameDao();
        _AllGames = _GameDao.getAllGames();
    }

    LiveData<List<Player>> getAllPlayers() {
        return _AllPlayers;
    }

    public void insertPlayer (Player player) {
        new insertPlayerAsyncTask(_PlayerDao).execute(player);
    }

    private class insertPlayerAsyncTask extends AsyncTask<Player, Void, Void>{
        private PlayerDao _AsyncTaskDao;
        public insertPlayerAsyncTask(PlayerDao playerDao) {
            _AsyncTaskDao = playerDao;
        }

        @Override
        protected Void doInBackground(final Player... params) {
            _AsyncTaskDao.insertPlayer(params[0]);
            return null;
        }
    }

    LiveData<List<Game>> getAllGames() {
        return _AllGames;
    }

    public void insertGame (Game game) {
        new insertGameAsyncTask(_GameDao).execute(game);
    }

    private class insertGameAsyncTask extends AsyncTask<Game, Void, Void>{
        private GameDao _AsyncTaskDao;
        public insertGameAsyncTask(GameDao gameDao) {
            _AsyncTaskDao = gameDao;
        }

        @Override
        protected Void doInBackground(final Game... params) {
            _AsyncTaskDao.insertGame(params[0]);
            return null;
        }
    }
}
